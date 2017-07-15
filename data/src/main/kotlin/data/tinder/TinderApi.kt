package data.tinder

import data.tinder.auth.AuthResponse
import data.tinder.auth.AuthenticationRequest
import data.tinder.like.LikeResponse
import data.tinder.recommendations.RecommendationResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

internal interface TinderApi {

    @Headers("Content-Type: application/json")
    @POST("/v2/auth/login/facebook")
    fun login(@Body request: AuthenticationRequest): Single<AuthResponse>

    @GET("/recs/core?locale=en")
    fun getRecommendations(): Single<RecommendationResponse>

    @GET("/like/{targetId}")
    fun like(@Path("targetId") targetId: String): Single<LikeResponse>

    companion object {
        const val BASE_URL = "https://api.gotinder.com"
        const val HEADER_AUTH = "X-Auth-Token"
    }
}
