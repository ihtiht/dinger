package data.network.tinder

import dagger.Module
import dagger.Provides
import data.account.AccountModule
import data.account.DingerAccountManager
import data.network.NetworkModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class, AccountModule::class))
internal class TinderApiModule {
    @Provides
    @Singleton
    fun tinderApi(retrofitBuilder: Retrofit.Builder, dingerAccountManager: DingerAccountManager)
            : TinderApi = retrofitBuilder
            .client(OkHttpClient.Builder()
                    .addInterceptor { it.proceed(it.request().newBuilder()
                            .apply {
                                dingerAccountManager.getAccountToken()?.let {
                                    addHeader(TinderApi.HEADER_AUTH, it)
                                }
                            }.build())
                    }.build())
            .baseUrl(TinderApi.BASE_URL)
            .build()
            .create(TinderApi::class.java)
}
