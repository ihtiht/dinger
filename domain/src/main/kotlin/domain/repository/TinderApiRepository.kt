package domain.repository

import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.recommendation.DomainRecommendation
import io.reactivex.Single

interface TinderApiRepository {
    fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>

    fun getRecommendations(): Single<Collection<DomainRecommendation>>
}
