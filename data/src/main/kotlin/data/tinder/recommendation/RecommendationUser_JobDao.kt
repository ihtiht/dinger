package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUser_JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser_Job(binding: RecommendationUserEntity_RecommendationUserJobEntity)

    @Query("SELECT * from RecommendationUserEntity_RecommendationUserJobEntity " +
            "WHERE recommendationUserEntityId=:id")
    fun selectUser_JobsByUserId(id: String)
            : List<RecommendationUserEntity_RecommendationUserJobEntity>
}
