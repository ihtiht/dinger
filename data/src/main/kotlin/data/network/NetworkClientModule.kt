package data.network

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import okhttp3.OkHttpClient
import reporter.CrashReporter
import reporter.OkHttpCrashReporterLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = arrayOf(FirebaseCrashReporterModule::class))
internal class NetworkClientModule {
    @Provides
    @Singleton
    fun client(crashReporter: CrashReporter): OkHttpClient.Builder = OkHttpClient.Builder()
            .addNetworkInterceptor(OkHttpCrashReporterLoggingInterceptor(crashReporter))
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private companion object {
        const val TIMEOUT_SECONDS = 45L
    }
}
