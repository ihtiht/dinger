package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUser_TeaserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser_Teaser(binding: RecommendationUserEntity_RecommendationUserTeaserEntity)

    @Query("SELECT * from RecommendationUserEntity_RecommendationUserTeaserEntity " +
            "WHERE recommendationUserEntityId=:id")
    fun selectUser_TeasersByUserId(id: String)
            : List<RecommendationUserEntity_RecommendationUserTeaserEntity>
}
