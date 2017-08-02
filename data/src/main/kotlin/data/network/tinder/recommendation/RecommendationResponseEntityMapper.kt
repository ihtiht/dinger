package data.network.tinder.recommendation

import data.network.common.EntityMapper
import domain.recommendation.DomainRecommendationCollection

internal class RecommendationResponseEntityMapper
    : EntityMapper<RecommendationResponse, DomainRecommendationCollection> {
    // TODO Properly parse the response
    override fun transform(source: RecommendationResponse) = DomainRecommendationCollection(listOf())
}
