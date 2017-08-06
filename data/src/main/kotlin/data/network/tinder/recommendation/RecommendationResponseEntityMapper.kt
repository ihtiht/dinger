package data.network.tinder.recommendation

import com.google.firebase.crash.FirebaseCrash
import data.network.common.EntityMapper
import domain.recommendation.DomainRecommendation
import domain.recommendation.DomainRecommendationCollection

internal class RecommendationResponseEntityMapper
    : EntityMapper<RecommendationResponse, DomainRecommendationCollection> {
    override fun transform(source: RecommendationResponse) = DomainRecommendationCollection(
            source.recommendations.let {
                when (it) {
                    null -> throw IllegalStateException(source.message ?:
                            "Unexpected 2xx recommendation response without message: $source")
                    else -> it.map { transformRecommendation(it) }
                            .filterNotNull()
                }
            })

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
