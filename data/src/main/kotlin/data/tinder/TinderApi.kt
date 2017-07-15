package data.tinder

import data.tinder.auth.AuthResponse
import data.tinder.auth.AuthenticationRequest
import data.tinder.like.LikeResponse
import data.tinder.recommendations.RecommendationResponse
import io.reactivex.Flowable
import retrofit2.http.*

internal interface TinderApi {

    @Headers("Content-Type: application/json")
    @POST("/auth")
    fun login(@Body request: AuthenticationRequest): Flowable<AuthResponse>

    @GET("/user/recs?locale=en")
    fun getRecommendations(): Flowable<RecommendationResponse>

    @GET("/like/{targetId}")
    fun like(@Path("targetId") targetId: String): Flowable<LikeResponse>

    companion object {
        const val BASE_URL = "https://api.gotinder.com"
        const val HEADER_AUTH = "X-Auth-Token"
    }
}
