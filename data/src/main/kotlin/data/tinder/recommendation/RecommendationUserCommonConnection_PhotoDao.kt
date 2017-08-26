package data.tinder.recommendation

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
internal interface RecommendationUserCommonConnection_PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommonConnection_Photo(bond: RecommendationUserCommonConnectionEntity_PhotoEntity)
}
