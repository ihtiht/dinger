package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
internal interface RecommendationUser_InterestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser_Interest(bond: RecommendationUserEntity_RecommendationInterestEntity)
}
