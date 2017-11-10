package data.tinder

import dagger.Module
import dagger.Provides
import data.account.AccountModule
import data.account.AppAccountAuthenticator
import data.network.NetworkClientModule
import data.network.NetworkModule
import okhttp3.OkHttpClient
import org.stoyicker.dinger.data.BuildConfig
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkClientModule::class, NetworkModule::class, AccountModule::class))
internal class TinderApiModule {
    @Provides
    @Singleton
    fun tinderApi(
            clientBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder,
            appAccountManagerImpl: AppAccountAuthenticator): TinderApi = retrofitBuilder
            .client(clientBuilder
                    .addNetworkInterceptor({
                        it.proceed(it.request().newBuilder()
                                .apply {
                                    appAccountManagerImpl.getAccountToken()?.let {
                                        addHeader(TinderApi.HEADER_AUTH, it)
                                        addHeader(TinderApi.HEADER_CONTENT_TYPE,
                                                TinderApi.CONTENT_TYPE_JSON)
                                        addHeader(TinderApi.HEADER_APP_VERSION,
                                                BuildConfig.TINDER_VERSION_CODE)
                                        addHeader(TinderApi.HEADER_PLATFORM,
                                                BuildConfig.PLATFORM_ANDROID)
                                    }
                                }
                                .build())
                    })
                    .build())
            .baseUrl(TinderApi.BASE_URL)
            .build()
            .create(TinderApi::class.java)
}
