package data.tinder.recommendation

import data.AppDatabase
import data.network.EntityMapper
import domain.recommendation.DomainRecommendationInstagram
import domain.recommendation.DomainRecommendationInstagramPhoto
import domain.recommendation.DomainRecommendationInterest
import domain.recommendation.DomainRecommendationJob
import domain.recommendation.DomainRecommendationPhoto
import domain.recommendation.DomainRecommendationProcessedFile
import domain.recommendation.DomainRecommendationSchool
import domain.recommendation.DomainRecommendationSpotifyAlbum
import domain.recommendation.DomainRecommendationSpotifyArtist
import domain.recommendation.DomainRecommendationSpotifyThemeTrack
import domain.recommendation.DomainRecommendationTeaser
import domain.recommendation.DomainRecommendationUser

internal class TopLevelRecommendationDao(
        appDatabase: AppDatabase,
        private val userMapper: EntityMapper<DomainRecommendationUser, RecommendationUserEntity>,
        private val instagramMapper: EntityMapper<DomainRecommendationInstagram,
                RecommendationUserInstagramEntity>,
        private val instagramPhotoMapper: EntityMapper<DomainRecommendationInstagramPhoto,
                RecommendationUserInstagramPhotoEntity>,
        private val processedFileMapper: EntityMapper<DomainRecommendationProcessedFile,
                RecommendationUserPhotoProcessedFileEntity>,
        private val artistMapper: EntityMapper<DomainRecommendationSpotifyArtist,
                RecommendationUserSpotifyThemeTrackArtistEntity>,
        private val spotifyThemeTrackMapper: EntityMapper<DomainRecommendationSpotifyThemeTrack,
                RecommendationUserSpotifyThemeTrackEntity>,
        private val albumMapper: EntityMapper<DomainRecommendationSpotifyAlbum,
                RecommendationUserSpotifyThemeTrackAlbumEntity>,
        private val photoMapper: EntityMapper<DomainRecommendationPhoto,
                RecommendationUserPhotoEntity>,
        private val interestMapper: EntityMapper<DomainRecommendationInterest,
                RecommendationInterestEntity>,
        private val jobMapper: EntityMapper<DomainRecommendationJob, RecommendationUserJobEntity>,
        private val schoolMapper: EntityMapper<DomainRecommendationSchool,
                RecommendationUserSchoolEntity>,
        private val teaserMapper: EntityMapper<DomainRecommendationTeaser,
                RecommendationUserTeaserEntity>)
    : RecommendationUserDao by RecommendationUserDao_Impl(appDatabase),
        RecommendationUserInstagramDao by RecommendationUserInstagramDao_Impl(appDatabase),
        RecommendationUserInstagramPhotoDao
        by RecommendationUserInstagramPhotoDao_Impl(appDatabase),
        RecommendationUserInstagram_PhotoDao
        by RecommendationUserInstagram_PhotoDao_Impl(appDatabase),
        RecommendationProcessedFileDao by RecommendationProcessedFileDao_Impl(appDatabase),
        RecommendationPhoto_ProcessedFileDao
        by RecommendationPhoto_ProcessedFileDao_Impl(appDatabase),
        RecommendationUserPhotoDao by RecommendationUserPhotoDao_Impl(appDatabase),
        RecommendationSpotifyArtistDao by RecommendationSpotifyArtistDao_Impl(appDatabase),
        RecommendationSpotifyThemeTrack_ArtistDao
        by RecommendationSpotifyThemeTrack_ArtistDao_Impl(appDatabase),
        RecommendationSpotifyAlbum_ProcessedFileDao
        by RecommendationSpotifyAlbum_ProcessedFileDao_Impl(appDatabase),
        RecommendationUserSpotifyThemeTrackDao
        by RecommendationUserSpotifyThemeTrackDao_Impl(appDatabase),
        RecommendationUserSpotifyThemeTrackAlbumDao
        by RecommendationUserSpotifyThemeTrackAlbumDao_Impl(appDatabase),
        RecommendationInterestDao by RecommendationInterestDao_Impl(appDatabase),
        RecommendationUser_InterestDao by RecommendationUser_InterestDao_Impl(appDatabase),
        RecommendationUser_PhotoDao by RecommendationUser_PhotoDao_Impl(appDatabase),
        RecommendationUserJobDao by RecommendationUserJobDao_Impl(appDatabase),
        RecommendationUser_JobDao by RecommendationUser_JobDao_Impl(appDatabase),
        RecommendationUserSchoolDao by RecommendationUserSchoolDao_Impl(appDatabase),
        RecommendationUser_SchoolDao by RecommendationUser_SchoolDao_Impl(appDatabase),
        RecommendationUserTeaserDao by RecommendationUserTeaserDao_Impl(appDatabase),
        RecommendationUser_TeaserDao by RecommendationUser_TeaserDao_Impl(appDatabase) {
    // TODO This parameter and its used classes should be of an INTERNAL type something like a RecommendationUserWithResolvedRelatives
    // and live in data since inserts can only be performed by the data module. Then in domain we
    // have a different one that only has the properties demanded by domain (including id). That
    // way we can refer to a user by an id without carrying all of its info around. Also this class
    // is for internal use only, which means this type can (and btw, should) be used by all methods
    // here
    fun insert(user: DomainRecommendationUser) {
        user.apply {
            instagram.let {
                insertInstagram(instagramMapper.from(it))
                it.photos.forEach {
                    insertInstagramPhoto(instagramPhotoMapper.from(it))
                    insertInstagram_Photo(
                            RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity(
                                    recommendationUserInstagramEntityUsername = instagram.username,
                                    recommendationUserInstagramPhotoEntityLink = it.link))
                }
            }
            insertTeaser(teaserMapper.from(teaser))
            spotifyThemeTrack.let {
                insertSpotifyThemeTrack(spotifyThemeTrackMapper.from(it))
                it.artists.forEach {
                    insertArtist(artistMapper.from(it))
                    insertSpotifyThemeTrack_Artist(
                            RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity(
                                    recommendationUserSpotifyThemeTrackEntityId
                                        = spotifyThemeTrack.id,
                                    recommendationUserSpotifyThemeTrackArtistEntityId = it.id))
                }
                it.album.let { album ->
                    insertAlbum(albumMapper.from(album))
                    album.images.forEach {
                        insertProcessedFile(processedFileMapper.from(it))
                        insertSpotifyAlbum_ProcessedFile(
                                RecommendationUserSpotifyThemeTrackAlbumEntity_RecommendationUserPhotoProcessedFileEntity(
                                        recommendationUserSpotifyThemeTrackAlbumEntityId = album.id,
                                        recommendationUserPhotoProcessedFileEntityUrl = it.url))
                    }
                }
            }
            insertUser(userMapper.from(this))
            commonInterests.forEach {
                insertInterest(interestMapper.from(it))
                insertUser_Interest(RecommendationUserEntity_RecommendationInterestEntity(
                        recommendationUserEntityId = id,
                        recommendationInterestEntityId = it.id))
            }
            photos.forEach { photo ->
                insertPhoto(photoMapper.from(photo))
                photo.processedFiles.forEach {
                    insertProcessedFile(processedFileMapper.from(it))
                    insertPhoto_ProcessedFile(
                            RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity(
                                    recommendationUserPhotoEntityId = photo.id,
                                    recommendationUserPhotoProcessedFileEntityUrl = it.url))
                }
                insertUser_Photo(RecommendationUserEntity_RecommendationUserPhotoEntity(
                        recommendationUserEntityId = id,
                        recommendationUserPhotoEntityId = photo.id))
            }
            jobs.forEach {
                insertJob(jobMapper.from(it))
                insertUser_Job(RecommendationUserEntity_RecommendationUserJobEntity(
                        recommendationUserEntityId = id,
                        recommendationUserJobEntityId = it.id))
            }
            schools.forEach {
                insertSchool(schoolMapper.from(it))
                insertUser_School(RecommendationUserEntity_RecommendationUserSchoolEntity(
                        recommendationUserEntityId = id,
                        recommendationUserSchoolEntityId = it.id))
            }
            teasers.forEach {
                insertTeaser(teaserMapper.from(it))
                insertUser_Teaser(RecommendationUserEntity_RecommendationUserTeaserEntity(
                        recommendationUserEntityId = id,
                        recommendationUserTeaserEntityId = it.id))
            }
        }
    }

//    fun selectById(id: String): LiveData<List<DomainRecommendationUser>> {
//        throw NotImplementedError("Not yet done.")
//    }
//
//    fun selectByFilterOnName(filter: String): LiveData<List<DomainRecommendationUser>> {
//        throw NotImplementedError("Not yet done.")
//    }
}
