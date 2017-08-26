package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
internal interface RecommendationUserSchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchool(school: RecommendationUserSchoolEntity)
}
