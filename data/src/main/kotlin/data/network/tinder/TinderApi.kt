package data.network.tinder

import data.network.tinder.auth.AuthRequestParameters
import io.reactivex.Single
import okhttp3.ResponseBody
import org.stoyicker.dinger.data.BuildConfig
import retrofit2.http.*

internal interface TinderApi {
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("/v2/auth/login/facebook")
    fun login(@Body requestParameters: AuthRequestParameters): Single<ResponseBody>

    @GET("/recs/core?locale=${BuildConfig.TINDER_API_LOCALE}")
    fun getRecommendations(): Single<ResponseBody>

    @GET("/like/{targetId}")
    fun like(@Path("targetId") targetId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL = "https://api.gotinder.com"
        const val HEADER_AUTH = "X-Auth-Token"
    }
}
