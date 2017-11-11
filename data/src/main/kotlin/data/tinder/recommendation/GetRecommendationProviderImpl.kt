package data.tinder.recommendation

import domain.recommendation.DomainRecommendationUser
import domain.recommendation.GetRecommendationProvider
import io.reactivex.Single
import reporter.CrashReporter

internal class GetRecommendationProviderImpl(
        private val getRecommendationFacade: GetRecommendationFacade,
        private val crashReporter: CrashReporter) : GetRecommendationProvider {
    override fun getRecommendations(): Single<List<DomainRecommendationUser>> =
            getRecommendationFacade.fetch(Unit).doOnError { crashReporter.report(it) }
}
