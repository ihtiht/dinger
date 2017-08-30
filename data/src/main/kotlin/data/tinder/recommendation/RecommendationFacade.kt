package data.tinder.recommendation

import data.ObjectMapper
import data.network.RequestFacade
import domain.recommendation.DomainRecommendationUser

internal class RecommendationFacade(
        source: RecommendationSource,
        requestMapper: ObjectMapper<Unit, Unit>,
        responseMapper: ObjectMapper<RecommendationResponse, Collection<DomainRecommendationUser>>)
    : RequestFacade<Unit, Unit, RecommendationResponse, Collection<DomainRecommendationUser>>(
        source, requestMapper, responseMapper)
