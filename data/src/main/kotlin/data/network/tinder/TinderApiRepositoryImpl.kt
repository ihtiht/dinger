package data.network.tinder

import com.google.firebase.crash.FirebaseCrash
import data.ComponentHolder
import data.network.tinder.auth.AuthFacade
import data.network.tinder.recommendation.RecommendationFacade
import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.recommendation.DomainRecommendationCollection
import domain.recommendation.DomainRecommendationRequestParameters
import domain.repository.TinderApiRepository
import io.reactivex.Single
import javax.inject.Inject

internal class TinderApiRepositoryImpl : TinderApiRepository {
    @Inject
    lateinit var loginFacade: AuthFacade
    @Inject
    lateinit var recommendationFacade: RecommendationFacade

    init {
          ComponentHolder.tinderRepositoryComponent.inject(this)
    }

    override fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>
            = loginFacade.fetch(parameters).doOnError { FirebaseCrash.report(it) }

    override fun getRecommendations(parameters: DomainRecommendationRequestParameters)
            : Single<DomainRecommendationCollection>
            = recommendationFacade.fetch(parameters).doOnError { FirebaseCrash.report(it) }
}
