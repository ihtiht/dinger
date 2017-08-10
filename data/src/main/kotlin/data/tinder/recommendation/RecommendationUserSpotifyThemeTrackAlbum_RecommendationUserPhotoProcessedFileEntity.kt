package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("recommendationUserSpotifyThemeTrackAlbumEntityId")))
internal class
RecommendationUserSpotifyThemeTrackAlbum_RecommendationUserPhotoProcessedFileEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var recommendationUserSpotifyThemeTrackAlbumEntityId: String,
    var recommendationUserPhotoProcessedFileEntity: String)
