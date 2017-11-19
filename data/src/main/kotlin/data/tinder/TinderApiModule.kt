package data.tinder

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.facebook.AccessToken
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import data.RootModule
import data.account.AccountModule
import data.account.AppAccountAuthenticator
import data.crash.FirebaseCrashReporterModule
import data.network.NetworkClientModule
import data.network.NetworkModule
import data.notification.NotificationManager
import data.notification.NotificationManagerModule
import data.tinder.login.LoginRequestParameters
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.stoyicker.dinger.data.BuildConfig
import org.stoyicker.dinger.data.R
import reporter.CrashReporter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(
        AccountModule::class,
        FirebaseCrashReporterModule::class,
        NetworkClientModule::class,
        NetworkModule::class,
        NotificationManagerModule::class,
        RootModule::class))
internal class TinderApiModule {
    @Provides
    @Singleton
    @Suppress("UNREACHABLE_CODE")
    fun tinderApi(
            context: Context,
            clientBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder,
            appAccountManager: AppAccountAuthenticator,
            notificationManager: NotificationManager,
            crashReporter: CrashReporter): TinderApi = retrofitBuilder
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
            }.authenticator { _, response ->
                return@authenticator when (response.code()) {
                    401 -> appAccountManager.let {
                        val facebookToken: AccessToken? = AccessToken.getCurrentAccessToken()
                        if (facebookToken != null) {
                            AccessToken.setCurrentAccessToken(facebookToken)
                            Single.create<AccessToken> { emitter ->
                                AccessToken.refreshCurrentAccessTokenAsync(
                                        object : AccessToken.AccessTokenRefreshCallback {
                                            @Suppress("FunctionName")
                                            override fun OnTokenRefreshed(
                                                    accessToken: AccessToken) {
                                                emitter.onSuccess(accessToken)
                                            }

                                            @Suppress("FunctionName")
                                            override fun OnTokenRefreshFailed(
                                                    exception: FacebookException) {
                                                emitter.onError(exception)
                                            }
                                        })
                            }.run {
                                try {
                                    blockingGet().let {
                                        AccessToken.setCurrentAccessToken(it)
                                        authenticatorRequest(it)
                                    }
                                } catch (exception: FacebookException) {
                                    crashReporter.report(exception)
                                    finishAccount(context, appAccountManager, notificationManager)
                                    null
                                }
                            }
                        } else {
                            finishAccount(context, appAccountManager, notificationManager)
                            null
                        }
                    }
                    else -> {
                        finishAccount(context, appAccountManager, notificationManager)
                        null
                    }
                }
            }.build())
            .baseUrl(TinderApi.BASE_URL)
            .build()
            .create(TinderApi::class.java)
}

private fun authenticatorRequest(accessToken: AccessToken) = Request.Builder().post(
        RequestBody.create(MediaType.parse("application/json; charset=UTF-8"),
                Moshi.Builder()
                        .build()
                        .adapter(LoginRequestParameters::class.java)
                        .toJson(LoginRequestParameters(
                                facebookId = accessToken.userId,
                                facebookToken = accessToken.token))))
        .url("https://api.gotinder.com/v2/auth/login/facebook")
        .build()

private fun finishAccount(
        context: Context,
        appAccountAuthenticator: AppAccountAuthenticator,
        notificationManager: NotificationManager) {
    LoginManager.getInstance().logOut()
    appAccountAuthenticator.removeAccount()
    notificationManager.notify(
            channelName = R.string.authentication_notification_channel_name,
            title = R.string.authentication_notification_title,
            body = R.string.authentication_notification_body,
            category = NotificationManager.CATEGORY_RECOMMENDATION,
            priority = NotificationManager.PRIORITY_HIGH,
            clickHandler = PendingIntent.getActivity(
                    context,
                    1,
                    Intent("org.stoyicker.action.HOME"),
                    PendingIntent.FLAG_UPDATE_CURRENT))
}
