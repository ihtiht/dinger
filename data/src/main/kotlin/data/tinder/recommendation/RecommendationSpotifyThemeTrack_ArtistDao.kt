package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationSpotifyThemeTrack_ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpotifyThemeTrack_Artist(
            bond: RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity)

    @Query("SELECT * from RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity " +
            "WHERE recommendationUserSpotifyThemeTrackEntityId=:id")
    fun selectSpotifyThemeTrack_ArtistsBySpotifyThemeTrackId(id: String)
            : List<RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity>
}
