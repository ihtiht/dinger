package data.tinder.recommendation

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RecommendationUserPhotoEntity): Long

    @Query("SELECT * from RecommendationUserPhotoEntity WHERE id=:id")
    fun selectById(id: String): LiveData<List<RecommendationUserPhotoWithRelatives>>
}
