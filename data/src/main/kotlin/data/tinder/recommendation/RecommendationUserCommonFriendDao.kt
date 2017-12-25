package data.tinder.recommendation

import android.arch.persistence.room.*

@Dao
internal interface RecommendationUserCommonFriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommonFriend(commonFriend: RecommendationUserCommonFriendEntity)

    @Query("SELECT * from RecommendationUserCommonFriendEntity WHERE id=:id")
    @Transaction
    fun selectCommonFriendById(id: String)
            : List<RecommendationUserCommonFriendWithRelatives>
}
