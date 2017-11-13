package data.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class NetworkClientModule {
    @Provides
    @Singleton
    fun client(): OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private companion object {
        const val TIMEOUT_SECONDS = 45L
    }
}
