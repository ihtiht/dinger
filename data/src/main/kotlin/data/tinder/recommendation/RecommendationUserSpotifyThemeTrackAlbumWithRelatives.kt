package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserSpotifyThemeTrackAlbumWithRelatives(
        @Embedded
        var recommendationUserSpotifyThemeTrackAlbum: RecommendationUserSpotifyThemeTrackAlbum,
        @Relation(parentColumn = "id", entityColumn = "url")
        var images: Set<RecommendationUserPhotoProcessedFileEntity>)
