package data.tinder.recommendation

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserInstagramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInstagram(instagram: RecommendationUserInstagramEntity)

    @Query("SELECT * from RecommendationUserInstagramEntity WHERE username=:username")
    fun selectInstagramByUsername(username: String)
            : LiveData<List<RecommendationUserInstagramWithRelatives>>
}
