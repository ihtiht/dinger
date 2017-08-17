package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationPhoto_ProcessedFileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto_ProcessedFile(
            binding: RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity)

    @Query("SELECT * from RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity " +
            "WHERE recommendationUserPhotoEntityId=:id")
    fun selectPhoto_ProcessedFilesByPhotoId(id: String)
            : List<RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity>
}
