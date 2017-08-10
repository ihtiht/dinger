package data.tinder.recommendation

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface RecommendationUserSpotifyThemeTrackAlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RecommendationUserSpotifyThemeTrackAlbumEntity): Long

    @Query("SELECT * from RecommendationUserSpotifyThemeTrackAlbumEntity WHERE id=:id")
    fun selectById(id: String): LiveData<List<RecommendationUserSpotifyThemeTrackAlbumWithRelatives>>
}
