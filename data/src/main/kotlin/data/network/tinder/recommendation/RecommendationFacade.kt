package data.network.tinder.recommendation

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import domain.recommendation.DomainRecommendationCollection
import domain.recommendation.DomainRecommendationRequestParameters

internal class RecommendationFacade constructor(
        source: RecommendationSource,
        requestMapper: EntityMapper<DomainRecommendationRequestParameters,
                RecommendationRequestParameters>,
        responseMapper: EntityMapper<RecommendationResponse, DomainRecommendationCollection>)
    : RequestFacade<
        DomainRecommendationRequestParameters, RecommendationRequestParameters,
        RecommendationResponse, DomainRecommendationCollection>(
        source, requestMapper, responseMapper)
