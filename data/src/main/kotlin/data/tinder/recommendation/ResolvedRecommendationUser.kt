package data.tinder.recommendation

import java.util.Date

internal class ResolvedRecommendationUser(
        val distanceMiles: Int,
        val commonConnections: Iterable<ResolvedRecommendationCommonConnection>,
        val connectionCount: Int,
        val id: String,
        val birthDate: Date,
        val name: String,
        val instagram: ResolvedRecommendationInstagram,
        val teaser: ResolvedRecommendationTeaser,
        val spotifyThemeTrack: ResolvedRecommendationSpotifyThemeTrack,
        val gender: Int,
        val birthDateInfo: String,
        val contentHash: String,
        val groupMatched: Boolean,
        val pingTime: Date,
        val sNumber: Int,
        val liked: Boolean,
        val commonInterests: Iterable<ResolvedRecommendationInterest>,
        val photos: Iterable<ResolvedRecommendationPhoto>,
        val jobs: Iterable<ResolvedRecommendationJob>,
        val schools: Iterable<ResolvedRecommendationSchool>,
        val teasers: Iterable<ResolvedRecommendationTeaser>)
