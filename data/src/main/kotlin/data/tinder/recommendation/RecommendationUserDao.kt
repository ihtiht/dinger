package data.tinder.recommendation

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: RecommendationUserEntity)

    @Query("SELECT * from RecommendationUserEntity WHERE id=:id")
    fun selectUserById(id: String): LiveData<List<RecommendationUserWithRelatives>>

    @Query("SELECT * from RecommendationUserEntity WHERE instr(name, :filter) > 0")
    fun selectUsersByFilterOnName(filter: String): LiveData<List<RecommendationUserWithRelatives>>
}
