package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUser_SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser_School(binding: RecommendationUserEntity_RecommendationUserSchoolEntity)

    @Query("SELECT * from RecommendationUserEntity_RecommendationUserSchoolEntity " +
            "WHERE recommendationUserEntityId=:id")
    fun selectUser_SchoolsByUserId(id: String)
            : List<RecommendationUserEntity_RecommendationUserSchoolEntity>
}
