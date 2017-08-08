package data.network.tinder.recommendation

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import domain.recommendation.DomainRecommendation

internal class RecommendationFacade constructor(
        source: RecommendationSource,
        requestMapper: EntityMapper<Unit, Unit>,
        responseMapper: EntityMapper<RecommendationResponse, Collection<DomainRecommendation>>)
    : RequestFacade<Unit, Unit, RecommendationResponse, Collection<DomainRecommendation>>(
        source, requestMapper, responseMapper)
