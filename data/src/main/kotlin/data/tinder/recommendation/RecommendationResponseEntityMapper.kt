package data.tinder.recommendation

import data.EntityMapper
import domain.DomainException
import domain.recommendation.DomainRecommendation
import reporter.CrashReporter

internal class RecommendationResponseEntityMapper(
        private val crashReporter: CrashReporter,
        private val eventTracker: RecommendationEventTracker)
    : EntityMapper<RecommendationResponse, Collection<DomainRecommendation>> {
    override fun from(source: RecommendationResponse): Collection<DomainRecommendation> {
        source.let {
            eventTracker.track(it)
            return it.recommendations.let {
                when (it) {
                    null -> throw when (source.message) {
                        is String -> DomainException(source.message)
                        else -> IllegalStateException(
                                "Unexpected 2xx recommendation response without message: $source")
                    }
                    else -> it.mapNotNull { transformRecommendation(it) }
                }
            }.toHashSet()
        }
    }

    private fun transformRecommendation(source: Recommendation) =
            when (source.type) {
                "user" -> source.user.let { DomainRecommendation(it.id, it.name) }
                else -> {
                    crashReporter.report(IllegalStateException(
                            "Unexpected recommendation type ${source.type}"))
                    null
                }
            }
}
