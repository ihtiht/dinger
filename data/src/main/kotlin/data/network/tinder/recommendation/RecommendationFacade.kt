package data.network.tinder.recommendation

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import domain.recommendation.DomainRecommendationCollection

internal class RecommendationFacade constructor(
        source: RecommendationSource,
        requestMapper: EntityMapper<Unit, Unit>,
        responseMapper: EntityMapper<RecommendationResponse, DomainRecommendationCollection>)
    : RequestFacade<Unit, Unit, RecommendationResponse, DomainRecommendationCollection>(
        source, requestMapper, responseMapper)
