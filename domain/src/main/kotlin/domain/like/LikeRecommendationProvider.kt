package domain.like

import domain.recommendation.DomainRecommendationUser
import io.reactivex.Single

interface LikeRecommendationProvider {
    fun likeRecommendation(recommendation: DomainRecommendationUser)
            : Single<DomainLikedRecommendationAnswer>
}
