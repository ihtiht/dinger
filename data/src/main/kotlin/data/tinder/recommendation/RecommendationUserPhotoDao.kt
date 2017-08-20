package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: RecommendationUserPhotoEntity)

    @Query("SELECT * from RecommendationUserPhotoEntity WHERE id=:id")
    fun selectPhotoById(id: String): List<RecommendationUserPhotoWithRelatives>
}
