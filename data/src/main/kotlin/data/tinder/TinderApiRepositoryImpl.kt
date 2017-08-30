package data.tinder

import data.ComponentHolder
import data.tinder.auth.AuthFacade
import data.tinder.like.LikeFacade
import data.tinder.recommendation.RecommendationFacade
import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.like.DomainLikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser
import domain.repository.TinderApiRepository
import io.reactivex.Single
import reporter.CrashReporter
import javax.inject.Inject

internal class TinderApiRepositoryImpl : TinderApiRepository {
    @Inject
    lateinit var loginFacade: AuthFacade
    @Inject
    lateinit var recommendationFacade: RecommendationFacade
    @Inject
    lateinit var likeFacade: LikeFacade
    @Inject
    lateinit var crashReporter: CrashReporter

    init {
        ComponentHolder.tinderRepositoryComponent.inject(this)
    }

    override fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser> =
            loginFacade.fetch(parameters).doOnError { crashReporter.report(it) }

    override fun getRecommendations(): Single<Collection<DomainRecommendationUser>> =
            recommendationFacade.fetch(Unit).doOnError { crashReporter.report(it) }

    override fun like(recommendation: DomainRecommendationUser)
            : Single<DomainLikedRecommendationAnswer> =
            likeFacade.fetch(recommendation).doOnError { crashReporter.report(it) }
}
