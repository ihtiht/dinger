package domain.repository

import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.recommendation.DomainRecommendationCollection
import domain.recommendation.DomainRecommendationRequestParameters
import io.reactivex.Single

interface TinderApiRepository {
    fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>

    fun getRecommendations(parameters: DomainRecommendationRequestParameters)
            : Single<DomainRecommendationCollection>
}
