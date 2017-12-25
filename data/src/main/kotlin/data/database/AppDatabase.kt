package data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import data.tinder.recommendation.*

@Database(version = 1,
        entities = [
                // For RecommendationUserWithRelatives
                RecommendationUserEntity::class,
                RecommendationUserInstagramEntity::class,
                RecommendationUserInstagramPhotoEntity::class,
                RecommendationUserTeaserEntity::class,
                RecommendationUserSpotifyThemeTrackEntity::class,
                RecommendationUserSpotifyThemeTrackAlbumEntity::class,
                RecommendationUserEntity_RecommendationUserCommonFriendEntity::class,
                RecommendationUserEntity_RecommendationLikeEntity::class,
                RecommendationUserEntity_RecommendationUserPhotoEntity::class,
                RecommendationUserEntity_RecommendationUserJobEntity::class,
                RecommendationUserEntity_RecommendationUserSchoolEntity::class,
                RecommendationUserEntity_RecommendationUserTeaserEntity::class,
                // For RecommendationUserInstagramWithRelatives
                RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity::class,
                // For RecommendationUserPhotoWithRelatives
                RecommendationUserPhotoProcessedFileEntity::class,
                RecommendationUserPhotoEntity::class,
                RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity::class,
                // For RecommendationUserSpotifyThemeTrackWithRelatives
                RecommendationUserSpotifyThemeTrackArtistEntity::class,
                RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity::class,
                // For RecommendationUserPhotoProcessedFileEntity
                RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity::class,
                // For RecommendationUserCommonFriendEntity
                RecommendationUserCommonFriendEntity::class,
                RecommendationUserCommonFriendPhotoEntity::class,
                RecommendationUserCommonFriendEntity_PhotoEntity::class,
                // For RecommendationLikeEntity
                RecommendationLikeEntity::class,
                // For RecommendationUserJobEntity
                RecommendationUserJobEntity::class,
                // For RecommendationUserSchoolEntity
                RecommendationUserSchoolEntity::class])
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun recommendationUserDao(): RecommendationUserDao

    abstract fun recommendationUserInstagramDao(): RecommendationUserInstagramDao

    abstract fun recommendationUserInstagramPhotoDao(): RecommendationUserInstagramPhotoDao

    abstract fun recommendationUserInstagram_PhotoDao(): RecommendationUserInstagram_InstagramPhotoDao

    abstract fun recommendationProcessedFileDao(): RecommendationProcessedFileDao

    abstract fun recommendationPhoto_ProcessedFileDao(): RecommendationPhoto_ProcessedFileDao

    abstract fun recommendationUserPhotoDao(): RecommendationUserPhotoDao

    abstract fun recommendationUserSpotifyThemeTrackDao(): RecommendationUserSpotifyThemeTrackDao

    abstract fun recommendationSpotifyArtistDao(): RecommendationSpotifyArtistDao

    abstract fun recommendationSpotifyThemeTrack_ArtistDao()
            : RecommendationSpotifyThemeTrack_ArtistDao

    abstract fun recommendationSpotifyAlbum_ProcessedFileDao()
            : RecommendationSpotifyAlbum_ProcessedFileDao

    abstract fun recommendationUserSpotifyThemeTrackAlbumDao()
            : RecommendationUserSpotifyThemeTrackAlbumDao

    abstract fun recommendationUserCommonFriendDao(): RecommendationUserCommonFriendDao

    abstract fun recommendationUser_RecommendationUserCommonFriendDao():
            RecommendationUser_RecommendationUserCommonFriendDao

    abstract fun recommendationUserCommonFriendPhotoDao()
            : RecommendationUserCommonFriendPhotoDao

    abstract fun recommendationUserCommonFriend_PhotoDao()
            : RecommendationUserCommonFriend_PhotoDao

    abstract fun recommendationLikeDao(): RecommendationLikeDao

    abstract fun recommendationUser_LikeDao(): RecommendationUser_LikeDao

    abstract fun recommendationUser_PhotoDao(): RecommendationUser_PhotoDao

    abstract fun recommendationUserJobDao(): RecommendationUserJobDao

    abstract fun recommendationUser_JobDao(): RecommendationUser_JobDao

    abstract fun recommendationUserSchoolDao(): RecommendationUserSchoolDao

    abstract fun recommendationUser_SchoolDao(): RecommendationUser_SchoolDao

    abstract fun recommendationUserTeaserDao(): RecommendationUserTeaserDao

    abstract fun recommendationUser_TeaserDao(): RecommendationUser_TeaserDao
}
