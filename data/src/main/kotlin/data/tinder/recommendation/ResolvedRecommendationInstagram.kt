package data.tinder.recommendation

import java.util.Date

internal class ResolvedRecommendationInstagram(
        val profilePictureUrl: String,
        val lastFetchTime: Date,
        val mediaCount: Int,
        val completedInitialFetch: Boolean,
        val username: String,
        val photos: Iterable<ResolvedRecommendationInstagramPhoto>)
