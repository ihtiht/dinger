package data.tinder.recommendation

import java.util.Date

internal class ResolvedRecommendationUser(
        val distanceMiles: Int,
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
        val sNumber: Long,
        val liked: Boolean,
        val commonInterests: Set<ResolvedRecommendationInterest>,
        val photos: Set<ResolvedRecommendationPhoto>,
        val jobs: Set<ResolvedRecommendationJob>,
        val schools: Set<ResolvedRecommendationSchool>,
        val teasers: Set<ResolvedRecommendationTeaser>)
