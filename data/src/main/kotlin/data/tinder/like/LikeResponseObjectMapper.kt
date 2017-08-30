package data.tinder.like

import data.ObjectMapper
import domain.like.DomainLikedRecommendationAnswer

internal class LikeResponseObjectMapper : ObjectMapper<LikeResponse, DomainLikedRecommendationAnswer> {
    override fun from(source: LikeResponse) = DomainLikedRecommendationAnswer(source.match)
}
