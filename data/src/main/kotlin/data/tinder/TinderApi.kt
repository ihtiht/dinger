package data.tinder

import data.tinder.auth.AuthRequestParameters
import io.reactivex.Single
import okhttp3.ResponseBody
import org.stoyicker.dinger.data.BuildConfig
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface TinderApi {
    @POST("/v2/auth/login/facebook")
    fun login(@Body requestParameters: AuthRequestParameters): Single<ResponseBody>

    @GET("/recs/core?locale=${BuildConfig.TINDER_API_LOCALE}")
    fun getRecommendations(): Single<ResponseBody>

    @GET("/like/{targetId}")
    fun like(@Path("targetId") targetId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL = "https://api.gotinder.com"
        const val HEADER_AUTH = "X-Auth-Token"
        const val HEADER_APP_VERSION = "app-version"
        const val HEADER_PLATFORM = "platform"
    }
}
