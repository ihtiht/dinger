package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserSpotifyThemeTrackWithRelatives(
        @Embedded
        var recommendationUserSpotifyThemeTrackEntity: RecommendationUserSpotifyThemeTrackEntity,
        @Relation(parentColumn = "id", entityColumn = "id")
        var artists: Set<RecommendationUserSpotifyThemeTrackArtistEntity>)
