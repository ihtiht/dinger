package data.network

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import reporter.CrashReporter
import tracker.TracedException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = arrayOf(FirebaseCrashReporterModule::class))
internal class NetworkClientModule {
    @Provides
    @Singleton
    fun client(crashReporter: CrashReporter): OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor { chain ->
                chain.request().let { request ->
                    chain.proceed(request).also { response ->
                            val requestBuffer = Buffer().also { request.body()?.writeTo(it) }
                            crashReporter.report(TrackedNetworkRequestTracedException("""
                                Method: ${request.method()}
                                Url: ${request.url().encodedPath()}
                                Code: ${response.code()}
                                Request body: ${requestBuffer.readUtf8().takeUnless {
                                it.toString().isBlank()
                            } ?: "EMPTY"}
                                Response body: ${response
                                    .peekBody(1 * 1024 * 1024)
                                    ?.string() ?: "EMPTY"}
                                Cache-Control: ${response.cacheControl().takeUnless {
                                it.toString().isBlank()
                            } ?: "EMPTY"}
                                Headers: ${response.headers().takeUnless {
                                it.toString().isBlank()
                            } ?: "NONE"}
                                """))
                        }
                    }
                }
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private companion object {
        const val TIMEOUT_SECONDS = 45L
    }
}

private class TrackedNetworkRequestTracedException(message: String) : TracedException(message)
