package data.tinder.dislike

import domain.dislike.DislikeRecommendationProvider
import domain.dislike.DomainDislikedRecommendationAnswer
import domain.recommendation.DomainRecommendationUser
import io.reactivex.Single
import reporter.CrashReporter

internal class DislikeRecommendationProviderImpl(
        private val dislikeFacade: DislikeFacade,
        private val crashReporter: CrashReporter) : DislikeRecommendationProvider {
    override fun dislikeRecommendation(recommendation: DomainRecommendationUser)
            : Single<DomainDislikedRecommendationAnswer> =
            dislikeFacade.fetch(recommendation).doOnError { crashReporter.report(it) }
}
