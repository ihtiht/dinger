package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUser_InterestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser_Interest(binding: RecommendationUserEntity_RecommendationInterestEntity)

    @Query("SELECT * from RecommendationUserEntity_RecommendationInterestEntity " +
            "WHERE recommendationUserEntityId=:id")
    fun selectUser_InterestsByUserId(id: String)
            : List<RecommendationUserEntity_RecommendationInterestEntity>
}
