package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("id"), Index("album")),
        foreignKeys = arrayOf(ForeignKey(
                entity = RecommendationUserSpotifyThemeTrackAlbumEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("album"))))
internal class RecommendationUserSpotifyThemeTrackEntity(
        var album: String,
        var previewUrl: String?,
        var name: String,
        @PrimaryKey
        var id: String,
        var uri: String) {
    companion object {
        val NONE = RecommendationUserSpotifyThemeTrackEntity(album = "",
                previewUrl = null,
                name = "",
                id = "",
                uri = "")
    }
}
