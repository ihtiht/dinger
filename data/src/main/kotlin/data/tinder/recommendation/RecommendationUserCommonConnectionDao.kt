package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserCommonFriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommonFriend(commonFriend: RecommendationUserCommonFriendEntity)

    @Query("SELECT * from RecommendationUserCommonFriendEntity WHERE id=:id")
    fun selectCommonFriendById(id: String)
            : List<RecommendationUserCommonFriendWithRelatives>
}
