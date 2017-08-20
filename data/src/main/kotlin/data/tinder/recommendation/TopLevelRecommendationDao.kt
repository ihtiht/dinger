package data.tinder.recommendation

import android.arch.lifecycle.Transformations
import android.support.annotation.WorkerThread
import data.AppDatabase
import data.network.EntityMapper
import java.util.Date

@WorkerThread
internal class TopLevelRecommendationDao(
        appDatabase: AppDatabase,
        private val userMapper: EntityMapper<ResolvedRecommendationUser, RecommendationUserEntity>,
        private val instagramMapper: EntityMapper<ResolvedRecommendationInstagram,
                RecommendationUserInstagramEntity>,
        private val instagramPhotoMapper: EntityMapper<ResolvedRecommendationInstagramPhoto,
                RecommendationUserInstagramPhotoEntity>,
        private val processedFileMapper: EntityMapper<ResolvedRecommendationProcessedFile,
                RecommendationUserPhotoProcessedFileEntity>,
        private val artistMapper: EntityMapper<ResolvedRecommendationSpotifyArtist,
                RecommendationUserSpotifyThemeTrackArtistEntity>,
        private val spotifyThemeTrackMapper: EntityMapper<ResolvedRecommendationSpotifyThemeTrack,
                RecommendationUserSpotifyThemeTrackEntity>,
        private val albumMapper: EntityMapper<ResolvedRecommendationSpotifyAlbum,
                RecommendationUserSpotifyThemeTrackAlbumEntity>,
        private val photoMapper: EntityMapper<ResolvedRecommendationPhoto,
                RecommendationUserPhotoEntity>,
        private val interestMapper: EntityMapper<ResolvedRecommendationInterest,
                RecommendationInterestEntity>,
        private val jobMapper: EntityMapper<ResolvedRecommendationJob, RecommendationUserJobEntity>,
        private val schoolMapper: EntityMapper<ResolvedRecommendationSchool,
                RecommendationUserSchoolEntity>,
        private val teaserMapper: EntityMapper<ResolvedRecommendationTeaser,
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
    fun insert(user: ResolvedRecommendationUser) {
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
        user.photos.forEach {
            insertPhoto(photoMapper.from(it))
            insertUser_Photo(RecommendationUserEntity_RecommendationUserPhotoEntity(
                    recommendationUserEntityId = user.id,
                    recommendationUserPhotoEntityId = it.id
            ))
        }
    }

    fun selectById(id: String) =
            Transformations.map(selectUserById(id)) { it.map { resolveUser(it) } }

    fun selectByFilterOnName(filter: String) =
            Transformations.map(selectUsersByFilterOnName(filter)) { it.map { resolveUser(it) } }

    private fun resolveUser(source: RecommendationUserWithRelatives): ResolvedRecommendationUser {
        var distanceMiles = 0
        var connectionCount = 0
        var id = ""
        var birthDate = Date()
        var name = ""
        var instagram = ResolvedRecommendationInstagram(
                profilePictureUrl = "",
                lastFetchTime = Date(),
                mediaCount = 0,
                completedInitialFetch = false,
                username = "",
                photos = emptySet())
        var teaser = ResolvedRecommendationTeaser(
                id = "",
                description = "",
                type = "")
        var spotifyThemeTrack = ResolvedRecommendationSpotifyThemeTrack(
                artists = emptySet(),
                album = ResolvedRecommendationSpotifyAlbum(
                        id = "",
                        name = "",
                        images = emptySet()),
                previewUrl = "",
                name = "",
                id = "",
                uri = "")
        var gender = 0
        var birthDateInfo = ""
        var contentHash = ""
        var groupMatched = false
        var pingTime = Date()
        var sNumber = 0
        var liked = false
        var photos = emptySet<ResolvedRecommendationPhoto>()
        var commonInterests = emptySet<ResolvedRecommendationInterest>()
        var jobs = emptySet<ResolvedRecommendationJob>()
        var schools = emptySet<ResolvedRecommendationSchool>()
        var teasers = emptySet<ResolvedRecommendationTeaser>()
        source.recommendationUserEntity.let {
            distanceMiles = it.distanceMiles
            connectionCount = it.connectionCount
            id = it.id
            birthDate = it.birthDate
            name = it.name
            selectInstagramByUsername(it.instagram).firstOrNull()?.let {
                var profilePictureUrl = ""
                var lastFetchTime = Date()
                var mediaCount = 0
                var completedInitialFetch = false
                var username = ""
                var photos = emptySet<ResolvedRecommendationInstagramPhoto>()
                it.recommendationUserInstagram.let {
                    profilePictureUrl = it.profilePictureUrl
                    lastFetchTime = it.lastFetchTime
                    mediaCount = it.mediaCount
                    completedInitialFetch = it.completedInitialFetch
                    username = it.username
                }
                it.photos.forEach {
                    selectInstagram_PhotosByInstagramUsername(it).forEach {
                        selectInstagramPhotoByLink(
                                it.recommendationUserInstagramPhotoEntityLink).firstOrNull()?.let {
                            photos += ResolvedRecommendationInstagramPhoto(
                                    link = it.link,
                                    imageUrl = it.imageUrl,
                                    thumbnailUrl = it.thumbnailUrl,
                                    ts = it.ts)
                        }
                    }
                }
                instagram = ResolvedRecommendationInstagram(
                        profilePictureUrl = profilePictureUrl,
                        lastFetchTime = lastFetchTime,
                        mediaCount = mediaCount,
                        completedInitialFetch = completedInitialFetch,
                        username = username,
                        photos = photos)
            }
            selectTeaserById(it.teaser).firstOrNull()?.let {
                teaser = ResolvedRecommendationTeaser(
                        id = it.id,
                        description = it.description,
                        type = it.type)
            }
            selectSpotifyThemeTrackById(it.spotifyThemeTrack).firstOrNull()?.let {
                var artists = emptySet<ResolvedRecommendationSpotifyArtist>()
                var album = ResolvedRecommendationSpotifyAlbum(
                        id = "",
                        name = "",
                        images = emptySet())
                var previewUrl = ""
                var name = ""
                var id = ""
                var uri = ""
                it.recommendationUserSpotifyThemeTrackEntity.let {
                    previewUrl = it.previewUrl
                    name = it.name
                    id = it.id
                    uri = it.id
                    selectAlbumById(it.album).firstOrNull()?.let {
                        var id = ""
                        var name = ""
                        var images = emptySet<ResolvedRecommendationProcessedFile>()
                        it.recommendationUserSpotifyThemeTrackAlbum.let {
                            id = it.id
                            name = it.name
                        }
                        it.images.forEach {
                            selectSpotifyAlbum_ProcessedFilesBySpotifyAlbumId(it).forEach {
                                selectProcessedFileByUrl(
                                        it.recommendationUserPhotoProcessedFileEntityUrl)
                                        .firstOrNull()?.let {
                                    images += ResolvedRecommendationProcessedFile(
                                            widthPx = it.widthPx,
                                            url = it.url,
                                            heightPx = it.heightPx)
                                }
                            }
                        }
                        album = ResolvedRecommendationSpotifyAlbum(
                                id = id,
                                name = name,
                                images = images)
                    }
                }
                it.artists.forEach {
                    selectSpotifyThemeTrack_ArtistsBySpotifyThemeTrackId(it).forEach {
                        selectArtistById(it.recommendationUserSpotifyThemeTrackArtistEntityId)
                                .firstOrNull()?.let {
                            artists += ResolvedRecommendationSpotifyArtist(
                                    id = it.id,
                                    name = it.name)
                        }
                    }
                }
                spotifyThemeTrack = ResolvedRecommendationSpotifyThemeTrack(
                        artists = artists,
                        album = album,
                        previewUrl = previewUrl,
                        name = name,
                        id = id,
                        uri = uri)
            }
            gender = it.gender
            birthDateInfo = it.birthDateInfo
            contentHash = it.contentHash
            groupMatched = it.groupMatched
            pingTime = it.pingTime
            sNumber = it.sNumber
            liked = it.liked
        }
        source.photos.forEach {
            selectUser_PhotosByUserId(it).forEach {
                selectPhotoById(it.recommendationUserPhotoEntityId).firstOrNull()?.let {
                    var id = ""
                    var url = ""
                    var processedFiles = emptySet<ResolvedRecommendationProcessedFile>()
                    it.recommendationUserPhotoEntity.let {
                        id = it.id
                        url = it.url
                    }
                    it.processedFiles.forEach {
                        selectPhoto_ProcessedFilesByPhotoId(it).forEach {
                            selectProcessedFileByUrl(
                                    it.recommendationUserPhotoProcessedFileEntityUrl)
                                    .firstOrNull()?.let {
                                processedFiles += ResolvedRecommendationProcessedFile(
                                        widthPx = it.widthPx,
                                        url = it.url,
                                        heightPx = it.heightPx)
                            }
                        }
                    }
                    photos += ResolvedRecommendationPhoto(
                            id = id,
                            url = url,
                            processedFiles = processedFiles)
                }
            }
        }
        source.commonInterests.forEach {
            selectUser_InterestsByUserId(it).forEach {
                selectInterestById(it.recommendationInterestEntityId).forEach {
                    commonInterests += ResolvedRecommendationInterest(id = it.id, name = it.name)
                }
            }
        }
        source.jobs.forEach {
            selectUser_JobsByUserId(it).forEach {
                selectJobById(it.recommendationUserJobEntityId).forEach {
                    jobs += ResolvedRecommendationJob(
                            id = it.id,
                            company = ResolvedRecommendationCompany(it.company.name),
                            title = ResolvedRecommendationTitle(it.title.name))
                }
            }
        }
        source.schools.forEach {
            selectUser_SchoolsByUserId(it).forEach {
                selectSchoolById(it.recommendationUserSchoolEntityId).forEach {
                    schools += ResolvedRecommendationSchool(id = it.id, name = it.name)
                }
            }
        }
        source.teasers.forEach {
            selectUser_TeasersByUserId(it).forEach {
                selectTeaserById(it.recommendationUserTeaserEntityId).forEach {
                    teasers += ResolvedRecommendationTeaser(
                            id = it.id,
                            description = it.description,
                            type = it.type)
                }
            }
        }
        return ResolvedRecommendationUser(
                distanceMiles = distanceMiles,
                connectionCount = connectionCount,
                id = id,
                birthDate = birthDate,
                name = name,
                instagram = instagram,
                teaser = teaser,
                spotifyThemeTrack = spotifyThemeTrack,
                gender = gender,
                birthDateInfo = birthDateInfo,
                contentHash = contentHash,
                groupMatched = groupMatched,
                pingTime = pingTime,
                sNumber = sNumber,
                liked = liked,
                photos = photos,
                commonInterests = commonInterests,
                jobs = jobs,
                schools = schools,
                teasers = teasers)
    }
}
