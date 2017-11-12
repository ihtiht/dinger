package domain.like

data class DomainLikedRecommendationAnswer(val matched: Boolean, val rateLimitedUntilMillis: Long?)
