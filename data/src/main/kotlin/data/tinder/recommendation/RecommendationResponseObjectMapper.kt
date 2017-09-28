package data.tinder.recommendation

import data.ObjectMapper
import domain.DomainException
import domain.recommendation.*
import reporter.CrashReporter

internal class RecommendationResponseObjectMapper(
        private val crashReporter: CrashReporter,
        private val eventTracker: RecommendationEventTracker,
        private val commonConnectionDelegate
        : ObjectMapper<RecommendationUserCommonConnection, DomainRecommendationCommonConnection>,
        private val instagramDelegate
        : ObjectMapper<RecommendationUserInstagram?, DomainRecommendationInstagram?>,
        private val teaserDelegate
        : ObjectMapper<RecommendationUserTeaser, DomainRecommendationTeaser>,
        private val spotifyThemeTrackDelegate
        : ObjectMapper<RecommendationUserSpotifyThemeTrack?, DomainRecommendationSpotifyThemeTrack?>,
        private val commonInterestDelegate
        : ObjectMapper<RecommendationInterest, DomainRecommendationInterest>,
        private val photoDelegate
        : ObjectMapper<RecommendationUserPhoto, DomainRecommendationPhoto>,
        private val jobDelegate: ObjectMapper<RecommendationUserJob, DomainRecommendationJob>,
        private val schoolDelegate: ObjectMapper<RecommendationUserSchool,
                DomainRecommendationSchool>)
    : ObjectMapper<RecommendationResponse, Collection<DomainRecommendationUser>> {
    override fun from(source: RecommendationResponse): Collection<DomainRecommendationUser> =
            source.let {
                eventTracker.track(it)
                return it.recommendations.let {
                    when (it) {
                        null -> throw when (source.message) {
                            is String -> DomainException(source.message)
                            else -> IllegalStateException(
                                    "Unexpected 2xx recommendation response without message: $source")
                        }
                        else -> it.mapNotNull { transformRecommendation(it) }
                    }
                }.toHashSet()
            }

    private fun transformRecommendation(source: Recommendation) = when (source.type) {
        "user" -> source.user.let {
            DomainRecommendationUser(
                    distanceMiles = it.distanceMiles,
                    commonConnections = it.commonConnections.map {
                        commonConnectionDelegate.from(it)
                    },
                    connectionCount = it.connectionCount,
                    id = it.id,
                    birthDate = it.birthDate,
                    name = it.name,
                    instagram = instagramDelegate.from(it.instagram),
                    teaser = teaserDelegate.from(it.teaser),
                    spotifyThemeTrack = spotifyThemeTrackDelegate.from(it.spotifyThemeTrack),
                    gender = it.gender,
                    birthDateInfo = it.birthDateInfo,
                    contentHash = it.contentHash,
                    groupMatched = it.groupMatched,
                    pingTime = it.pingTime,
                    sNumber = it.sNumber,
                    commonInterests = it.commonInterests.map {
                        commonInterestDelegate.from(it)
                    },
                    photos = it.photos.map { photoDelegate.from(it) },
                    jobs = it.jobs.map { jobDelegate.from(it) },
                    schools = it.schools.map { schoolDelegate.from(it) },
                    teasers = it.teasers.map { teaserDelegate.from(it) })
        }
        else -> {
            crashReporter.report(IllegalStateException(
                    "Unexpected recommendation type ${source.type}"))
            null
        }
    }
}

internal class RecommendationUserCommonConnectionObjectMapper(
        private val commonConnectionPhotoDelegate
        : ObjectMapper<RecommendationUserCommonConnectionPhoto, DomainRecommendationCommonConnectionPhoto>)
    : ObjectMapper<RecommendationUserCommonConnection, DomainRecommendationCommonConnection> {
    override fun from(source: RecommendationUserCommonConnection) =
            DomainRecommendationCommonConnection(
                    id = source.id,
                    name = source.name,
                    degree = source.degree,
                    photos = source.photos.map { commonConnectionPhotoDelegate.from(it) })
}

internal class RecommendationUserCommonConnectionPhotoObjectMapper
    : ObjectMapper<RecommendationUserCommonConnectionPhoto, DomainRecommendationCommonConnectionPhoto> {
    override fun from(source: RecommendationUserCommonConnectionPhoto) =
            DomainRecommendationCommonConnectionPhoto(
                    small = source.small,
                    medium = source.medium,
                    large = source.large)
}

internal class RecommendationInstagramObjectMapper(
        private val instagramPhotoDelegate
        : ObjectMapper<RecommendationUserInstagramPhoto, DomainRecommendationInstagramPhoto>)
    : ObjectMapper<RecommendationUserInstagram?, DomainRecommendationInstagram?> {
    override fun from(source: RecommendationUserInstagram?) = source?.let {
        DomainRecommendationInstagram(
                profilePictureUrl = it.profilePictureUrl,
                lastFetchTime = it.lastFetchTime,
                mediaCount = it.mediaCount,
                completedInitialFetch = it.completedInitialFetch,
                username = it.username,
                photos = it.photos.map { instagramPhotoDelegate.from(it) })
    }
}

internal class RecommendationInstagramPhotoObjectMapper
    : ObjectMapper<RecommendationUserInstagramPhoto, DomainRecommendationInstagramPhoto> {
    override fun from(source: RecommendationUserInstagramPhoto) =
            DomainRecommendationInstagramPhoto(
                    link = source.link,
                    imageUrl = source.imageUrl,
                    thumbnailUrl = source.thumbnailUrl,
                    ts = source.ts)
}

internal class RecommendationTeaserObjectMapper
    : ObjectMapper<RecommendationUserTeaser, DomainRecommendationTeaser> {
    override fun from(source: RecommendationUserTeaser) =
            DomainRecommendationTeaser(
                    id = RecommendationUserTeaserEntity.createId(
                            description = source.description,
                            type = source.type),
                    description = source.description,
                    type = source.type)
}

internal class RecommendationSpotifyThemeTrackObjectMapper(
        private val albumDelegate
        : ObjectMapper<RecommendationUserSpotifyThemeTrackAlbum, DomainRecommendationSpotifyAlbum>,
        private val artistDelegate
        : ObjectMapper<RecommendationUserSpotifyThemeTrackArtist, DomainRecommendationSpotifyArtist>)
    : ObjectMapper<RecommendationUserSpotifyThemeTrack?, DomainRecommendationSpotifyThemeTrack?> {
    override fun from(source: RecommendationUserSpotifyThemeTrack?) = source?.let {
        DomainRecommendationSpotifyThemeTrack(
                album = albumDelegate.from(it.album),
                artists = it.artists.map { artistDelegate.from(it) },
                id = it.id,
                name = it.name,
                previewUrl = it.previewUrl,
                uri = it.uri)
    }
}

internal class RecommendationSpotifyThemeTrackAlbumObjectMapper(
        private val processedFileDelegate
        : ObjectMapper<RecommendationUserPhotoProcessedFile, DomainRecommendationProcessedFile>)
    : ObjectMapper<RecommendationUserSpotifyThemeTrackAlbum, DomainRecommendationSpotifyAlbum> {
    override fun from(source: RecommendationUserSpotifyThemeTrackAlbum) =
            DomainRecommendationSpotifyAlbum(
                    id = source.id,
                    name = source.name,
                    images = source.images.map { processedFileDelegate.from(it) })
}

internal class RecommendationProcessedFileObjectMapper
    : ObjectMapper<RecommendationUserPhotoProcessedFile, DomainRecommendationProcessedFile> {
    override fun from(source: RecommendationUserPhotoProcessedFile) =
            DomainRecommendationProcessedFile(
                    widthPx = source.widthPx,
                    url = source.url,
                    heightPx = source.heightPx)
}

internal class RecommendationSpotifyThemeTrackArtistObjectMapper
    : ObjectMapper<RecommendationUserSpotifyThemeTrackArtist, DomainRecommendationSpotifyArtist> {
    override fun from(source: RecommendationUserSpotifyThemeTrackArtist) =
            DomainRecommendationSpotifyArtist(id = source.id, name = source.name)
}

internal class RecommendationInterestObjectMapper
    : ObjectMapper<RecommendationInterest, DomainRecommendationInterest> {
    override fun from(source: RecommendationInterest) =
            DomainRecommendationInterest(id = source.id, name = source.name)
}

internal class RecommendationPhotoObjectMapper(
        private val processedFileDelegate
        : ObjectMapper<RecommendationUserPhotoProcessedFile, DomainRecommendationProcessedFile>)
    : ObjectMapper<RecommendationUserPhoto, DomainRecommendationPhoto> {
    override fun from(source: RecommendationUserPhoto) = DomainRecommendationPhoto(
            id = source.id,
            url = source.url,
            processedFiles = source.processedFiles.map { processedFileDelegate.from(it) }
    )
}

internal class RecommendationJobObjectMapper(
        private val companyDelegate: ObjectMapper<RecommendationUserJobCompany, DomainRecommendationCompany>,
        private val titleDelegate: ObjectMapper<RecommendationUserJobTitle, DomainRecommendationTitle>)
    : ObjectMapper<RecommendationUserJob, DomainRecommendationJob> {
    override fun from(source: RecommendationUserJob) = DomainRecommendationJob(
            id = RecommendationUserJob.createId(source.company, source.title),
            company = source.company?.let { companyDelegate.from(it) },
            title = source.title?.let { titleDelegate.from(it) })
}

internal class RecommendationJobCompanyObjectMapper
    : ObjectMapper<RecommendationUserJobCompany, DomainRecommendationCompany> {
    override fun from(source: RecommendationUserJobCompany) = DomainRecommendationCompany(
            name = source.name
    )
}

internal class RecommendationJobTitleObjectMapper
    : ObjectMapper<RecommendationUserJobTitle, DomainRecommendationTitle> {
    override fun from(source: RecommendationUserJobTitle) = DomainRecommendationTitle(
            name = source.name
    )
}

internal class RecommendationSchoolObjectMapper
    : ObjectMapper<RecommendationUserSchool, DomainRecommendationSchool> {
    override fun from(source: RecommendationUserSchool) = DomainRecommendationSchool(
            id = source.id,
            name = source.name)
}
