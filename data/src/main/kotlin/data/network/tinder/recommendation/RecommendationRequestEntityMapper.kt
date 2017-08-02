package data.network.tinder.recommendation

import data.network.common.EntityMapper
import domain.recommendation.DomainRecommendationRequestParameters

internal class RecommendationRequestEntityMapper
    : EntityMapper<DomainRecommendationRequestParameters, RecommendationRequestParameters> {
    override fun transform(source: DomainRecommendationRequestParameters)
            = RecommendationRequestParameters()
}
