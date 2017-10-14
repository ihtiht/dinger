package data.tinder

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import data.account.AccountModule
import data.account.AppAccountAuthenticator
import data.network.NetworkClientModule
import data.network.NetworkModule
import data.tinder.login.LoginRequestParameters
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
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
            appAccountManager: AppAccountAuthenticator): TinderApi = retrofitBuilder
            .client(clientBuilder.addInterceptor {
                it.proceed(it.request().newBuilder()
                        .apply {
                            appAccountManager.getTinderAccountToken()?.let {
                                addHeader(TinderApi.HEADER_AUTH, it)
                                addHeader(TinderApi.HEADER_CONTENT_TYPE,
                                        TinderApi.CONTENT_TYPE_JSON)
                                addHeader(TinderApi.HEADER_PLATFORM,
                                        BuildConfig.PLATFORM_ANDROID)
                            }
                        }
                        .build())
            }
                    .authenticator {
                        _, response ->
                        return@authenticator when (response.code()) {
                            401 -> appAccountManager.let {
                                val facebookId = it.accountFacebookId()
                                val facebookToken by lazy { it.accountFacebookToken() }
                                if (facebookId != null && facebookToken != null) {
                                    Request.Builder()
                                            .post(RequestBody.create(
                                                    MediaType.parse(
                                                            "application/json; charset=UTF-8"),
                                                    Moshi.Builder().build()
                                                            .adapter(LoginRequestParameters::class.java)
                                                            .toJson(
                                                                    LoginRequestParameters(
                                                                            facebookId = facebookId,
                                                                            facebookToken = facebookToken))))
                                            .url("https://api.gotinder.com/v2/auth/login/facebook")
                                            .build()
                                } else {
                                    facebookId?.let { appAccountManager.removeAccount(it) }
                                    null
                                }
                            }
                            else -> null
                        }
                    }
                    .build())
            .baseUrl(TinderApi.BASE_URL)
            .build()
            .create(TinderApi::class.java)
}
