package data.tinder.recommendation

import com.google.firebase.crash.FirebaseCrash
import data.network.EntityMapper
import domain.DomainException
import domain.recommendation.DomainRecommendation

internal class RecommendationResponseEntityMapper
    : EntityMapper<RecommendationResponse, Collection<DomainRecommendation>> {
    override fun transform(source: RecommendationResponse) = source.recommendations.let {
        when (it) {
            null -> throw when (source.message) {
                null -> IllegalStateException(
                        "Unexpected 2xx recommendation response without message: $source")
                else -> DomainException(source.message)
            }
            else -> it.map { transformRecommendation(it) }.filterNotNull()
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
