package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationInterestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInterest(interest: RecommendationInterestEntity)

    @Query("SELECT * from RecommendationInterestEntity WHERE id=:id")
    fun selectInterestById(id: String): List<RecommendationInterestEntity>
}
