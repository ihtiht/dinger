package data.tinder.recommendation

import data.ObjectMapper
import data.network.RequestFacade

internal class RecommendationFacade(
        source: RecommendationSource,
        requestMapper: ObjectMapper<Unit, Unit>,
        responseMapper: ObjectMapper<RecommendationResponse, Collection<ResolvedRecommendationUser>>)
    : RequestFacade<Unit, Unit, RecommendationResponse, Collection<ResolvedRecommendationUser>>(
        source, requestMapper, responseMapper)
