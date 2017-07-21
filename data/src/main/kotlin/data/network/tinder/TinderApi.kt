package data.network.tinder

import data.network.tinder.auth.AuthRequestParameters
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

internal interface TinderApi {

    @Headers("Content-Type: application/json")
    @POST("/v2/auth/login/facebook")
    fun login(@Body requestParameters: AuthRequestParameters): Single<ResponseBody>

    @GET("/recs/core?locale=en")
    fun getRecommendations(): Single<ResponseBody>

    @GET("/like/{targetId}")
    fun like(@Path("targetId") targetId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL = "https://api.gotinder.com"
        const val HEADER_AUTH = "X-Auth-Token"
    }
}
