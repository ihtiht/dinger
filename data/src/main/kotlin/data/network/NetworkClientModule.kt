package data.network

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import reporter.CrashReporter
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = arrayOf(FirebaseCrashReporterModule::class))
internal class NetworkClientModule {
    @Provides
    @Singleton
    fun client(crashReporter: CrashReporter): OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                chain.request().let {
                    it.newBuilder().build().let { copy ->
                        crashReporter.report(IllegalMonitorStateException("""
                            Method: ${copy.method()}
                            Https: ${copy.isHttps}
                            Body: ${Buffer().let { buffer ->
                                copy.body()?.writeTo(buffer)
                                ?.also { buffer.readUtf8() } }}
                            Cache-Control: ${copy.cacheControl()}
                            Headers: ${copy.headers()}
                            Tag: ${copy.tag()}
                            Url: ${copy.url()}
                            """
                        ))
                    }
                    chain.proceed(it)
                }
                chain.proceed(chain.request()).also {
               crashReporter.report()
            } }
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private companion object {
        const val TIMEOUT_SECONDS = 45L
    }
}
