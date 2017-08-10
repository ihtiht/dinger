package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("recommendationUserSpotifyThemeTrackEntityId")))
internal class
RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var recommendationUserSpotifyThemeTrackEntityId: String,
    var recommendationUserSpotifyThemeTrackArtistEntityId: String)
