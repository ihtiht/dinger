package data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import data.tinder.recommendation.RecommendationTypeConverters
import data.tinder.recommendation.RecommendationUserDao
import data.tinder.recommendation.RecommendationUserEntity
import data.tinder.recommendation.RecommendationUserEntity_RecommendationInterestEntity
import data.tinder.recommendation.RecommendationUserEntity_RecommendationUserJobEntity
import data.tinder.recommendation.RecommendationUserEntity_RecommendationUserPhotoEntity
import data.tinder.recommendation.RecommendationUserEntity_RecommendationUserSchoolEntity
import data.tinder.recommendation.RecommendationUserEntity_RecommendationUserTeaserEntity
import data.tinder.recommendation.RecommendationUserInstagramDao
import data.tinder.recommendation.RecommendationUserInstagramEntity
import data.tinder.recommendation.RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity
import data.tinder.recommendation.RecommendationUserPhotoDao
import data.tinder.recommendation.RecommendationUserPhotoEntity
import data.tinder.recommendation.RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFile
import data.tinder.recommendation.RecommendationUserSpotifyThemeTrackAlbumDao
import data.tinder.recommendation.RecommendationUserSpotifyThemeTrackAlbumEntity
import data.tinder.recommendation.RecommendationUserSpotifyThemeTrackAlbum_RecommendationUserPhotoProcessedFileEntity
import data.tinder.recommendation.RecommendationUserSpotifyThemeTrackDao
import data.tinder.recommendation.RecommendationUserSpotifyThemeTrackEntity
import data.tinder.recommendation.RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity
import data.tinder.recommendation.RecommendationUserTeaserEntity

@Database(version = 1,
        entities = arrayOf(
                // For RecommendationUserWithRelatives
                RecommendationUserEntity::class,
                RecommendationUserInstagramEntity::class,
                RecommendationUserTeaserEntity::class,
                RecommendationUserSpotifyThemeTrackEntity::class,
                RecommendationUserSpotifyThemeTrackAlbumEntity::class,
                RecommendationUserEntity_RecommendationInterestEntity::class,
                RecommendationUserEntity_RecommendationUserPhotoEntity::class,
                RecommendationUserEntity_RecommendationUserJobEntity::class,
                RecommendationUserEntity_RecommendationUserSchoolEntity::class,
                RecommendationUserEntity_RecommendationUserTeaserEntity::class,
                // For RecommendationUserInstagramWithRelatives
                RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity::class,
                // For RecommendationUserPhotoWithRelatives
                RecommendationUserPhotoEntity::class,
                RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFile::class,
                // For RecommendationUserSpotifyThemeTrackWithRelatives
                RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity::class,
                // For RecommendationUserPhotoProcessedFileEntity
                RecommendationUserSpotifyThemeTrackAlbum_RecommendationUserPhotoProcessedFileEntity::class))
@TypeConverters(RecommendationTypeConverters.Companion::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun recommendationUserDao(): RecommendationUserDao

    abstract fun recommendationUserInstagramDao(): RecommendationUserInstagramDao

    abstract fun recommendationUserPhotoDao(): RecommendationUserPhotoDao

    abstract fun recommendationUserSpotifyThemeTrackDao(): RecommendationUserSpotifyThemeTrackDao

    abstract fun recommendationUserSpotifyThemeTrackAlbumDao()
            : RecommendationUserSpotifyThemeTrackAlbumDao
}
