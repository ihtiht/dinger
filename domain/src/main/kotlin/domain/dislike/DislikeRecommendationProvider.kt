package domain.dislike

import domain.recommendation.DomainRecommendationUser
import io.reactivex.Single

interface DislikeRecommendationProvider {
    fun dislikeRecommendation(recommendation: DomainRecommendationUser)
            : Single<DomainDislikedRecommendationAnswer>
}
