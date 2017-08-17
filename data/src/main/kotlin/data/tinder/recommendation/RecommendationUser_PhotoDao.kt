package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUser_PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser_Photo(binding: RecommendationUserEntity_RecommendationUserPhotoEntity)

    @Query("SELECT * from RecommendationUserEntity_RecommendationUserPhotoEntity " +
            "WHERE recommendationUserEntityId=:id")
    fun selectUser_PhotosByUserId(id: String)
            : List<RecommendationUserEntity_RecommendationUserPhotoEntity>
}
