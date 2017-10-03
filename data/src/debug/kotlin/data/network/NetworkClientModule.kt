package data.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class NetworkClientModule {
    @Provides
    @Singleton
    fun client(): OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private companion object {
        const val TIMEOUT_SECONDS = 45L
    }
}
