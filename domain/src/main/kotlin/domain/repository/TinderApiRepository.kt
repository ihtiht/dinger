package domain.repository

import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.like.DomainLikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser
import io.reactivex.Single

interface TinderApiRepository {
    fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>

    fun getRecommendations(): Single<Collection<DomainRecommendationUser>>

    fun like(recommendation: DomainRecommendationUser): Single<DomainLikedRecommendationAnswer>
}
