package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
internal interface RecommendationUserJobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: RecommendationUserJobEntity)
}
