package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
internal interface RecommendationUserCommonConnectionPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: RecommendationUserCommonConnectionPhotoEntity)
}
