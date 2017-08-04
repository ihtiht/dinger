package data.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
internal class NetworkClientModule {
    @Provides
    @Singleton
    fun client() = OkHttpClient.Builder()
}
