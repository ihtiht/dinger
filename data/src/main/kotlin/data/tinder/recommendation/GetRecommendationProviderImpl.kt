package data.tinder.recommendation

import domain.recommendation.DomainRecommendationUser
import domain.recommendation.GetRecommendationProvider
import io.reactivex.Single
import reporter.CrashReporter

internal class GetRecommendationProviderImpl(
        private val recommendationFacade: RecommendationFacade,
        private val crashReporter: CrashReporter) : GetRecommendationProvider {
    override fun getRecommendations(): Single<Collection<DomainRecommendationUser>> =
            recommendationFacade.fetch(Unit).doOnError { crashReporter.report(it) }
}
