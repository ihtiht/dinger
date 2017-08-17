package domain.recommendation

import java.util.Date

// TODO Some of the props here are actually foreign keys, so change them to the corresponding
// objects when working on that
data class DomainRecommendationUser(
        val distanceMiles: Int,
        val connectionCount: Int,
        val id: String,
        val birthDate: Date,
        val name: String,
        val instagram: DomainRecommendationInstagram,
        val teaser: DomainRecommendationTeaser,
        val spotifyThemeTrack: DomainRecommendationSpotifyThemeTrack,
        val gender: Int,
        val birthDateInfo: String,
        val contentHash: String,
        val groupMatched: Boolean,
        val pingTime: Date,
        val sNumber: Long,
        val liked: Boolean,
        val commonInterests: Set<DomainRecommendationInterest>,
        val photos: Set<DomainRecommendationPhoto>,
        val jobs: Set<DomainRecommendationJob>,
        val schools: Set<DomainRecommendationSchool>,
        val teasers: Set<DomainRecommendationTeaser>)
