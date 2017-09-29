package data.tinder.like

import domain.like.DomainLikedRecommendationAnswer
import domain.like.LikeRecommendationProvider
import domain.recommendation.DomainRecommendationUser
import io.reactivex.Single
import reporter.CrashReporter

internal class LikeRecommendationProviderImpl(
        private val likeFacade: LikeFacade,
        private val crashReporter: CrashReporter) : LikeRecommendationProvider {
    override fun likeRecommendation(recommendation: DomainRecommendationUser)
            : Single<DomainLikedRecommendationAnswer> =
            likeFacade.fetch(recommendation).doOnError { crashReporter.report(it) }
}
