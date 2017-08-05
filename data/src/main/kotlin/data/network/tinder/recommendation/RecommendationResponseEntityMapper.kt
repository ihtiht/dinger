package data.network.tinder.recommendation

import data.network.common.EntityMapper
import domain.recommendation.DomainRecommendation
import domain.recommendation.DomainRecommendationCollection

internal class RecommendationResponseEntityMapper
    : EntityMapper<RecommendationResponse, DomainRecommendationCollection> {
    override fun transform(source: RecommendationResponse) = DomainRecommendationCollection(
            source.recommendations.map { transformRecommendation(it) })

    // TODO Properly parse recommendations
    private fun transformRecommendation(source: Recommendation) = DomainRecommendation(0)
}
