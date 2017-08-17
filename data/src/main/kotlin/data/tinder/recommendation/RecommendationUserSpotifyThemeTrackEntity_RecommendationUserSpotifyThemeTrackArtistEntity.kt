package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserSpotifyThemeTrackEntityId")),
        primaryKeys = arrayOf("recommendationUserSpotifyThemeTrackEntityId",
                "recommendationUserSpotifyThemeTrackArtistEntityId"))
internal class
RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity(
    var recommendationUserSpotifyThemeTrackEntityId: String,
    var recommendationUserSpotifyThemeTrackArtistEntityId: String)
