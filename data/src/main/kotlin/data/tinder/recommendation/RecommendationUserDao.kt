package data.tinder.recommendation

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RecommendationUserEntity): Long

    @Query("SELECT * from RecommendationUserEntity WHERE id=:id")
    fun selectById(id: String): LiveData<List<RecommendationUserWithRelatives>>

    @Query("SELECT * from RecommendationUserEntity WHERE instr(name, :filter) > 0")
    fun selectByFilterOnName(filter: String): LiveData<List<RecommendationUserWithRelatives>>
}
