package domain.recommendation

import java.util.Date

data class DomainRecommendationUser(
        val distanceMiles: Int,
        val commonFriends: Iterable<DomainRecommendationCommonFriend>,
        val friendCount: Int,
        val id: String,
        val birthDate: Date,
        val name: String,
        val instagram: DomainRecommendationInstagram?,
        val teaser: DomainRecommendationTeaser,
        val spotifyThemeTrack: DomainRecommendationSpotifyThemeTrack?,
        val gender: Int,
        val birthDateInfo: String,
        val contentHash: String,
        val groupMatched: Boolean,
        val pingTime: Date,
        val sNumber: Int,
        val liked: Boolean = false, // Recommendations are not liked by default
        var matched: Boolean = false, // Nor matched by default
        val commonLikes: Iterable<DomainRecommendationLike>,
        val photos: Iterable<DomainRecommendationPhoto>,
        val jobs: Iterable<DomainRecommendationJob>,
        val schools: Iterable<DomainRecommendationSchool>,
        val teasers: Iterable<DomainRecommendationTeaser>)
