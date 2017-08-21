package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserInstagram_InstagramPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInstagram_Photo(
            binding: RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity)

    @Query("SELECT * from RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity " +
            "WHERE recommendationUserInstagramEntityUsername=:username")
    fun selectInstagram_PhotosByInstagramUsername(username: String)
            : List<RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity>
}
