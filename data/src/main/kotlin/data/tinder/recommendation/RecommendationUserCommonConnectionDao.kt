package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserCommonConnectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommonConnection(commonConnection: RecommendationUserCommonConnectionEntity)

    @Query("SELECT * from RecommendationUserCommonConnectionEntity")
    fun selectAllCommonConnections(): List<RecommendationUserCommonConnectionWithRelatives>
}
