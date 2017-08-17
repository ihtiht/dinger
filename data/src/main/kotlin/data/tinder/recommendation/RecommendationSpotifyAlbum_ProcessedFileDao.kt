package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationSpotifyAlbum_ProcessedFileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpotifyAlbum_ProcessedFile(
            binding: RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity)

    @Query("SELECT * from RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity " +
            "WHERE recommendationUserSpotifyThemeTrackAlbumEntityId=:id")
    fun selectSpotifyAlbum_ProcessedFilesBySpotifyAlbumId(id: String)
            : List<RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity>
}
