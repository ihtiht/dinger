package data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import data.tinder.recommendation.*

@Database(version = 1,
        entities = arrayOf(RecommendationUserEntity::class,
                RecommendationUserInstagramEntity::class,
                RecommendationUserTeaserEntity::class,
                RecommendationUserSpotifyThemeTrackEntity::class,
                RecommendationUserSpotifyThemeTrackAlbumEntity::class))
@TypeConverters(RecommendationTypeConverters.Companion::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun recommendationDao(): RecommendationDao
}
