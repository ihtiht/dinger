package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import data.RootModule
import data.database.AppDatabase
import javax.inject.Singleton

@Module(includes = arrayOf(RootModule::class))
internal class RecommendationUserResolverModule {
    @Provides
    @Singleton
    fun recommendationUserDao(appDatabase: AppDatabase) = appDatabase.recommendationUserDao()

    @Provides
    @Singleton
    fun commonConnectionDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserCommonConnectionDao()

    @Provides
    @Singleton
    fun user_CommonConnectionDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUser_RecommendationUserCommonConnectionDao()

    @Provides
    @Singleton
    fun commonConnectionPhotoDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserCommonConnectionPhotoDao()

    @Provides
    @Singleton
    fun commonConnection_PhotoDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserCommonConnection_PhotoDao()

    @Provides
    @Singleton
    fun commonConnectionPhotoDaoDelegate(
            photoDao: RecommendationUserCommonConnectionPhotoDao,
            commonConnection_PhotoDao: RecommendationUserCommonConnection_PhotoDao) =
            CommonConnectionPhotoDaoDelegate(photoDao, commonConnection_PhotoDao)

    @Provides
    fun instagramDao(appDatabase: AppDatabase) = appDatabase.recommendationUserInstagramDao()

    @Provides
    @Singleton
    fun instagramPhotoDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserInstagramPhotoDao()

    @Provides
    @Singleton
    fun instagram_PhotoDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserInstagram_PhotoDao()

    @Provides
    @Singleton
    fun instagramPhotoDaoDelegate(
            instagramPhotoDao: RecommendationUserInstagramPhotoDao,
            instagram_PhotoDao: RecommendationUserInstagram_InstagramPhotoDao) =
            RecommendationInstagramPhotoDaoDelegate(instagramPhotoDao, instagram_PhotoDao)

    @Provides
    @Singleton
    fun instagramDaoDelegate(
            instagramDao: RecommendationUserInstagramDao,
            instagramPhotoDaoDelegate: RecommendationInstagramPhotoDaoDelegate) =
            RecommendationInstagramDaoDelegate(instagramDao, instagramPhotoDaoDelegate)

    @Provides
    @Singleton
    fun commonConnectionDaoDelegate(
            commonConnectionDao: RecommendationUserCommonConnectionDao,
            user_CommonConnectionDao: RecommendationUser_RecommendationUserCommonConnectionDao,
            commonConnectionPhotoDaoDelegate: CommonConnectionPhotoDaoDelegate) =
            RecommendationCommonConnectionDaoDelegate(
                    commonConnectionDao,
                    user_CommonConnectionDao,
                    commonConnectionPhotoDaoDelegate)

    @Provides
    @Singleton
    fun interestDao(appDatabase: AppDatabase) = appDatabase.recommendationInterestDao()

    @Provides
    @Singleton
    fun user_InterestDao(appDatabase: AppDatabase) = appDatabase.recommendationUser_InterestDao()

    @Provides
    @Singleton
    fun interestDaoDelegate(
            interestDao: RecommendationInterestDao,
            user_InterestDao: RecommendationUser_InterestDao) =
            RecommendationInterestDaoDelegate(
                    interestDao,
                    user_InterestDao)

    @Provides
    @Singleton
    fun photoDao(appDatabase: AppDatabase) = appDatabase.recommendationUserPhotoDao()

    @Provides
    @Singleton
    fun processedFileDao(appDatabase: AppDatabase) = appDatabase.recommendationProcessedFileDao()

    @Provides
    @Singleton
    fun photo_ProcessedFileDao(appDatabase: AppDatabase) =
            appDatabase.recommendationPhoto_ProcessedFileDao()

    @Provides
    @Singleton
    fun album_ProcessedFileDao(appDatabase: AppDatabase) =
            appDatabase.recommendationSpotifyAlbum_ProcessedFileDao()

    @Provides
    @Singleton
    fun processedFileDaoDelegate(
            processedFileDao: RecommendationProcessedFileDao,
            photo_ProcessedFileDao: RecommendationPhoto_ProcessedFileDao,
            album_ProcessedFileDao: RecommendationSpotifyAlbum_ProcessedFileDao) =
            RecommendationProcessedFileDaoDelegate(
                    processedFileDao,
                    photo_ProcessedFileDao,
                    album_ProcessedFileDao)

    @Provides
    @Singleton
    fun user_PhotoDao(appDatabase: AppDatabase) = appDatabase.recommendationUser_PhotoDao()

    @Provides
    @Singleton
    fun photoDaoDelegate(
            photoDao: RecommendationUserPhotoDao,
            processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate,
            user_PhotoDao: RecommendationUser_PhotoDao) = RecommendationPhotoDaoDelegate(
            photoDao,
            user_PhotoDao,
            processedFileDaoDelegate)

    @Provides
    @Singleton
    fun jobDao(appDatabase: AppDatabase) = appDatabase.recommendationUserJobDao()

    @Provides
    @Singleton
    fun user_JobDao(appDatabase: AppDatabase) = appDatabase.recommendationUser_JobDao()

    @Provides
    @Singleton
    fun jobDaoDelegate(jobDao: RecommendationUserJobDao, user_JobDao: RecommendationUser_JobDao) =
            RecommendationJobDaoDelegate(
                    jobDao,
                    user_JobDao)

    @Provides
    @Singleton
    fun schoolDao(appDatabase: AppDatabase) = appDatabase.recommendationUserSchoolDao()

    @Provides
    @Singleton
    fun user_SchoolDao(appDatabase: AppDatabase) = appDatabase.recommendationUser_SchoolDao()

    @Provides
    @Singleton
    fun schoolDaoDelegate(
            schoolDao: RecommendationUserSchoolDao,
            user_SchoolDao: RecommendationUser_SchoolDao) = RecommendationSchoolDaoDelegate(
            schoolDao,
            user_SchoolDao)

    @Provides
    @Singleton
    fun teaserDao(appDatabase: AppDatabase) = appDatabase.recommendationUserTeaserDao()

    @Provides
    @Singleton
    fun user_teaserDao(appDatabase: AppDatabase) = appDatabase.recommendationUser_TeaserDao()

    @Provides
    @Singleton
    fun teaserDaoDelegate(
            teaserDao: RecommendationUserTeaserDao,
            user_TeaserDao: RecommendationUser_TeaserDao) = RecommendationTeaserDaoDelegate(
            teaserDao,
            user_TeaserDao)

    @Provides
    @Singleton
    fun spotifyThemeTrackDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserSpotifyThemeTrackDao()

    @Provides
    @Singleton
    fun spotifyArtistDao(appDatabase: AppDatabase) = appDatabase.recommendationSpotifyArtistDao()

    @Provides
    @Singleton
    fun spotifyArtist_TrackDao(appDatabase: AppDatabase) =
            appDatabase.recommendationSpotifyThemeTrack_ArtistDao()

    @Provides
    @Singleton
    fun spotifyArtistDaoDelegate(
            spotifyArtistDao: RecommendationSpotifyArtistDao,
            spotifyThemeTrack_ArtistDao: RecommendationSpotifyThemeTrack_ArtistDao) =
            RecommendationSpotifyArtistDaoDelegate(
                    spotifyArtistDao,
                    spotifyThemeTrack_ArtistDao)

    @Provides
    @Singleton
    fun spotifyAlbumDao(appDatabase: AppDatabase) =
            appDatabase.recommendationUserSpotifyThemeTrackAlbumDao()

    @Provides
    @Singleton
    fun spotifyAlbumDaoDelegate(
            spotifyAlbumDao: RecommendationUserSpotifyThemeTrackAlbumDao,
            processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate) =
            RecommendationSpotifyAlbumDaoDelegate(
                    spotifyAlbumDao,
                    processedFileDaoDelegate)

    @Provides
    @Singleton
    fun spotifyThemeTrackDaoDelegate(
            spotifyThemeTrackDao: RecommendationUserSpotifyThemeTrackDao,
            spotifyAlbumDaoDelegate: RecommendationSpotifyAlbumDaoDelegate,
            spotifyArtistDaoDelegate: RecommendationSpotifyArtistDaoDelegate) =
            RecommendationSpotifyThemeTrackDaoDelegate(
                    spotifyThemeTrackDao,
                    spotifyArtistDaoDelegate,
                    spotifyAlbumDaoDelegate)

    @Provides
    @Singleton
    fun recommendationUserResolver(
            userDao: RecommendationUserDao,
            commonConnectionDaoDelegate: RecommendationCommonConnectionDaoDelegate,
            instagramDaoDelegate: RecommendationInstagramDaoDelegate,
            interestDaoDelegate: RecommendationInterestDaoDelegate,
            photoDaoDelegate: RecommendationPhotoDaoDelegate,
            jobDaoDelegate: RecommendationJobDaoDelegate,
            schoolDaoDelegate: RecommendationSchoolDaoDelegate,
            teaserDaoDelegate: RecommendationTeaserDaoDelegate,
            spotifyThemeTrackDaoDelegate: RecommendationSpotifyThemeTrackDaoDelegate) =
            RecommendationUserResolver(
                    userDao,
                    commonConnectionDaoDelegate,
                    instagramDaoDelegate,
                    interestDaoDelegate,
                    photoDaoDelegate,
                    jobDaoDelegate,
                    schoolDaoDelegate,
                    teaserDaoDelegate,
                    spotifyThemeTrackDaoDelegate)
}
