package data.tinder.recommendation

import com.google.firebase.crash.FirebaseCrash
import data.network.EntityMapper
import domain.DomainException
import domain.recommendation.DomainRecommendation

internal class RecommendationResponseEntityMapper
    : EntityMapper<RecommendationResponse, Collection<DomainRecommendation>> {
    override fun from(source: RecommendationResponse) = source.recommendations.let {
        when (it) {
            null -> throw when (source.message) {
                is String -> DomainException(source.message)
                else -> IllegalStateException(
                        "Unexpected 2xx recommendation response without message: $source")
            }
            else -> it.mapNotNull { transformRecommendation(it) }
        }
    }.toHashSet()

    private fun transformRecommendation(source: Recommendation) =
            when (source.type) {
                "user" -> source.user.let { DomainRecommendation(it.id, it.name) }
                else -> {
                    FirebaseCrash.report(IllegalStateException(
                            "Unexpected recommendation type ${source.type}"))
                    null
                }
            }
}
