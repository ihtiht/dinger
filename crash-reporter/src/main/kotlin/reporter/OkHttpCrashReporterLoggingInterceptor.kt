package reporter

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.internal.http.HttpHeaders
import okio.Buffer
import tracker.TracedException
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream

class OkHttpCrashReporterLoggingInterceptor(private val crashReporter: CrashReporter)
    : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val connection = chain.connection()
        val protocol = connection?.protocol() ?: Protocol.HTTP_1_1
        val message = StringBuilder("--> ${request.method()} ${request.url().encodedPath()} " +
                "$protocol\n")
        val requestHeaders = request.headers()

        var i = -1
        while (++i < requestHeaders.size()) {
            message.append("${requestHeaders.name(i)}: ${requestHeaders.value(i)}\n")
        }

        request.body()?.apply {
            val buffer = Buffer()
            writeTo(buffer)
            if (isPlaintext(buffer)) {
                var charset: Charset? = Charsets.UTF_8
                contentType()?.apply { charset = charset(charset) }
                message.append("${buffer.readString(charset!!)}\n")
                message.append(
                        "--> END ${request.method()} (${contentLength()}-byte body)\n")
            } else {
                message.append("--> END ${request.method()} " +
                        "(binary ${contentLength()}-byte body omitted)\n")
            }
        }

        val startNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            message.append("<-- HTTP FAILED: $e\n")
            crashReporter.report(TrackedNetworkRequestTracedException(message.toString()))
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        message.append("<-- ${response.code()} ${response.message()} " +
                "${response.request().url().encodedPath()} (${tookMs}ms)\n")

        val responseHeaders = response.headers()
        i = -1
        while (++i < responseHeaders.size()) {
            message.append("${responseHeaders.name(i)}: ${responseHeaders.value(i)}\n")
        }

        if (!HttpHeaders.hasBody(response)) {
            message.append("<-- END HTTP\n")
        } else if (bodyEncoded(responseHeaders)) {
            val source = response.body()!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            if (responseHeaders["Content-Encoding"].equals("gzip", ignoreCase = true)) {
                GZIPInputStream(buffer.clone().inputStream()).bufferedReader(Charsets.UTF_8).use {
                    it.readText()
                }.let { message.append("$it\n") }
            }
            message.append("<-- END HTTP (gzip-encoded body decoded)\n")
        } else {
            response.body()!!.apply {
                val source = source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer()
                var charset: Charset? = Charsets.UTF_8
                contentType()?.apply { charset = charset(charset) }
                if (!isPlaintext(buffer)) {
                    message.append("\n<-- END HTTP (binary ${buffer.size()}-byte body omitted)\n")
                    return response
                }

                if (contentLength() != 0L) {
                    message.append("${buffer.clone().readString(charset!!)}\n")
                }

                message.append("<-- END HTTP (${buffer.size()}-byte body)\n")
            }
        }
        crashReporter.report(TrackedNetworkRequestTracedException(message.toString()))
        return response
    }

    private class TrackedNetworkRequestTracedException(message: String) : TracedException(message)
}

private fun isPlaintext(buffer: Buffer): Boolean {
    try {
        val prefix = Buffer()
        val byteCount = Math.min(buffer.size(), 64)
        buffer.copyTo(prefix, 0, byteCount)
        (0..16).forEach {
            if (prefix.exhausted()) {
                return@forEach
            }
            val codePoint = prefix.readUtf8CodePoint()
            if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                return false
            }
        }
        return true
    } catch (e: EOFException) {
        return false // Truncated UTF-8 sequence.
    }
}

private fun bodyEncoded(headers: Headers): Boolean {
    val contentEncoding = headers.get("Content-Encoding")
    return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
}
