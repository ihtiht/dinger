package data.tinder.recommendation

import data.ObjectMapper
import domain.DomainException
    import reporter.CrashReporter

internal class RecommendationResponseObjectMapper(
        private val crashReporter: CrashReporter,
        private val eventTracker: RecommendationEventTracker,
        private val commonConnectionDelegate
        : ObjectMapper<RecommendationUserCommonConnection, ResolvedRecommendationCommonConnection>,
        private val instagramDelegate
        : ObjectMapper<RecommendationUserInstagram, ResolvedRecommendationInstagram>,
        private val teaserDelegate
        : ObjectMapper<RecommendationUserTeaser, ResolvedRecommendationTeaser>,
        private val spotifyThemeTrackDelegate
        : ObjectMapper<RecommendationUserSpotifyThemeTrack, ResolvedRecommendationSpotifyThemeTrack>,
        private val commonInterestDelegate
        : ObjectMapper<RecommendationInterest, ResolvedRecommendationInterest>,
        private val photoDelegate
        : ObjectMapper<RecommendationUserPhoto, ResolvedRecommendationPhoto>,
        private val jobDelegate: ObjectMapper<RecommendationUserJob, ResolvedRecommendationJob>,
        private val schoolDelegate: ObjectMapper<RecommendationUserSchool,
                ResolvedRecommendationSchool>)
    : ObjectMapper<RecommendationResponse, Collection<ResolvedRecommendationUser>> {
    override fun from(source: RecommendationResponse): Collection<ResolvedRecommendationUser> {
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
    }

    private fun transformRecommendation(source: Recommendation) = when (source.type) {
        "user" -> source.user.let { ResolvedRecommendationUser(
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
        : ObjectMapper<RecommendationUserCommonConnectionPhoto, ResolvedRecommendationCommonConnectionPhoto>)
    : ObjectMapper<RecommendationUserCommonConnection, ResolvedRecommendationCommonConnection> {
    override fun from(source: RecommendationUserCommonConnection) =
            ResolvedRecommendationCommonConnection(
                    id = source.id,
                    name = source.name,
                    degree = source.degree,
                    photos = source.photos.map { commonConnectionPhotoDelegate.from(it) })
}

internal class RecommendationUserCommonConnectionPhotoObjectMapper
    : ObjectMapper<RecommendationUserCommonConnectionPhoto, ResolvedRecommendationCommonConnectionPhoto> {
    override fun from(source: RecommendationUserCommonConnectionPhoto) =
            ResolvedRecommendationCommonConnectionPhoto(
                    small = source.small,
                    medium = source.medium,
                    large = source.large)
}

internal class RecommendationInstagramObjectMapper(
        private val instagramPhotoDelegate
        : ObjectMapper<RecommendationUserInstagramPhoto, ResolvedRecommendationInstagramPhoto>)
    : ObjectMapper<RecommendationUserInstagram, ResolvedRecommendationInstagram> {
    override fun from(source: RecommendationUserInstagram) =
            ResolvedRecommendationInstagram(
                    profilePictureUrl = source.profilePictureUrl,
                    lastFetchTime = source.lastFetchTime,
                    mediaCount = source.mediaCount,
                    completedInitialFetch = source.completedInitialFetch,
                    username = source.username,
                    photos = source.photos.map { instagramPhotoDelegate.from(it) })
}

internal class RecommendationInstagramPhotoObjectMapper
    : ObjectMapper<RecommendationUserInstagramPhoto, ResolvedRecommendationInstagramPhoto> {
    override fun from(source: RecommendationUserInstagramPhoto) =
            ResolvedRecommendationInstagramPhoto(
                    link = source.link,
                    imageUrl = source.imageUrl,
                    thumbnailUrl = source.thumbnailUrl,
                    ts = source.ts)
}

internal class RecommendationTeaserObjectMapper
    : ObjectMapper<RecommendationUserTeaser, ResolvedRecommendationTeaser> {
    override fun from(source: RecommendationUserTeaser) =
            ResolvedRecommendationTeaser(
                    id = RecommendationUserTeaserEntity.createId(
                            description = source.description,
                            type = source.type),
                    description = source.description,
                    type = source.type)
}

internal class RecommendationSpotifyThemeTrackObjectMapper(
        private val albumDelegate
        : ObjectMapper<RecommendationUserSpotifyThemeTrackAlbum, ResolvedRecommendationSpotifyAlbum>,
        private val artistDelegate
        : ObjectMapper<RecommendationUserSpotifyThemeTrackArtist, ResolvedRecommendationSpotifyArtist>)
    : ObjectMapper<RecommendationUserSpotifyThemeTrack, ResolvedRecommendationSpotifyThemeTrack> {
    override fun from(source: RecommendationUserSpotifyThemeTrack) =
            ResolvedRecommendationSpotifyThemeTrack(
                    album = albumDelegate.from(source.album),
                    artists = source.artists.map { artistDelegate.from(it) },
                    id = source.id,
                    name = source.name,
                    previewUrl = source.previewUrl,
                    uri = source.uri)
}

internal class RecommendationSpotifyThemeTrackAlbumObjectMapper(
        private val processedFileDelegate
        : ObjectMapper<RecommendationUserPhotoProcessedFile, ResolvedRecommendationProcessedFile>)
    : ObjectMapper<RecommendationUserSpotifyThemeTrackAlbum, ResolvedRecommendationSpotifyAlbum> {
    override fun from(source: RecommendationUserSpotifyThemeTrackAlbum) =
            ResolvedRecommendationSpotifyAlbum(
                    id = source.id,
                    name = source.name,
                    images = source.images.map { processedFileDelegate.from(it) })
}

internal class RecommendationProcessedFileObjectMapper
    : ObjectMapper<RecommendationUserPhotoProcessedFile, ResolvedRecommendationProcessedFile> {
    override fun from(source: RecommendationUserPhotoProcessedFile) =
            ResolvedRecommendationProcessedFile(
                    widthPx = source.widthPx,
                    url = source.url,
                    heightPx = source.heightPx)
}

internal class RecommendationSpotifyThemeTrackArtistObjectMapper
    : ObjectMapper<RecommendationUserSpotifyThemeTrackArtist, ResolvedRecommendationSpotifyArtist> {
    override fun from(source: RecommendationUserSpotifyThemeTrackArtist) =
            ResolvedRecommendationSpotifyArtist(id = source.id, name = source.name)
}

internal class RecommendationInterestObjectMapper
    : ObjectMapper<RecommendationInterest, ResolvedRecommendationInterest> {
    override fun from(source: RecommendationInterest) =
            ResolvedRecommendationInterest(id = source.id, name = source.name)
}

internal class RecommendationPhotoObjectMapper(
        private val processedFileDelegate
        : ObjectMapper<RecommendationUserPhotoProcessedFile, ResolvedRecommendationProcessedFile>)
    : ObjectMapper<RecommendationUserPhoto, ResolvedRecommendationPhoto> {
    override fun from(source: RecommendationUserPhoto) = ResolvedRecommendationPhoto(
            id = source.id,
            url = source.url,
            processedFiles = source.processedFiles.map { processedFileDelegate.from(it) }
    )
}

internal class RecommendationJobObjectMapper(
        private val companyDelegate: ObjectMapper<RecommendationUserJobCompany, ResolvedRecommendationCompany>,
        private val titleDelegate: ObjectMapper<RecommendationUserJobTitle, ResolvedRecommendationTitle>)
    : ObjectMapper<RecommendationUserJob, ResolvedRecommendationJob> {
    override fun from(source: RecommendationUserJob) = ResolvedRecommendationJob(
            id = RecommendationUserJob.createId(source.company, source.title),
            company = companyDelegate.from(source.company),
            title = titleDelegate.from(source.title)
    )
}

internal class RecommendationJobCompanyObjectMapper
    : ObjectMapper<RecommendationUserJobCompany, ResolvedRecommendationCompany> {
    override fun from(source: RecommendationUserJobCompany) = ResolvedRecommendationCompany(
        name = source.name
    )
}

internal class RecommendationJobTitleObjectMapper
    : ObjectMapper<RecommendationUserJobTitle, ResolvedRecommendationTitle> {
    override fun from(source: RecommendationUserJobTitle) = ResolvedRecommendationTitle(
            name = source.name
    )
}

internal class RecommendationSchoolObjectMapper
    : ObjectMapper<RecommendationUserSchool, ResolvedRecommendationSchool> {
    override fun from(source: RecommendationUserSchool) = ResolvedRecommendationSchool(
            id = source.id,
            name = source.name)
}
