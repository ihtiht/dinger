package data.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.stoyicker.dinger.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkClientModule::class))
internal class NetworkModule {
    @Provides
    @Singleton
    fun retrofitBuilder(client: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .validateEagerly(BuildConfig.DEBUG)

    @Provides
    @Singleton
    fun facadeProvider(): FacadeProviderImpl = FacadeProviderImpl()
}
