package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserSpotifyThemeTrackAlbumEntityId")),
        primaryKeys = arrayOf("recommendationUserSpotifyThemeTrackAlbumEntityId",
        "recommendationUserPhotoProcessedFileEntityUrl"))
internal class
RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity(
    var recommendationUserSpotifyThemeTrackAlbumEntityId: String,
    var recommendationUserPhotoProcessedFileEntityUrl: String)
