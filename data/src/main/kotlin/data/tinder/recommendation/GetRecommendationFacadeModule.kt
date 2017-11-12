package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Module(includes = arrayOf(RecommendationSourceModule::class,
        RecommendationEventTrackerModule::class))
internal class GetRecommendationFacadeModule {
    @Provides
    @Singleton
    fun requestObjectMapper() = RecommendationRequestObjectMapper()

    @Provides
    @Singleton
    fun commonConnectionPhotoObjectMapper() = RecommendationUserCommonConnectionPhotoObjectMapper()

    @Provides
    @Singleton
    fun commonConnectionObjectMapper(
            photoDelegate: RecommendationUserCommonConnectionPhotoObjectMapper) =
            RecommendationUserCommonConnectionObjectMapper(photoDelegate)

    @Provides
    @Singleton
    fun instagramPhotoObjectMapper() = RecommendationInstagramPhotoObjectMapper()

    @Provides
    @Singleton
    fun instagramObjectMapper(instagramPhotoDelegate: RecommendationInstagramPhotoObjectMapper) =
            RecommendationInstagramObjectMapper(instagramPhotoDelegate)

    @Provides
    @Singleton
    fun teaserObjectMapper() = RecommendationTeaserObjectMapper()

    @Provides
    @Singleton
    fun processedFileObjectMapper() = RecommendationProcessedFileObjectMapper()

    @Provides
    @Singleton
    fun spotifyAlbumObjectMapper(processedFileDelegate: RecommendationProcessedFileObjectMapper) =
            RecommendationSpotifyThemeTrackAlbumObjectMapper(processedFileDelegate)

    @Provides
    @Singleton
    fun spotifyArtistObjectMapper() = RecommendationSpotifyThemeTrackArtistObjectMapper()

    @Provides
    @Singleton
    fun spotifyThemeTrackObjectMapper(
            albumDelegate: RecommendationSpotifyThemeTrackAlbumObjectMapper,
            artistDelegate: RecommendationSpotifyThemeTrackArtistObjectMapper) =
            RecommendationSpotifyThemeTrackObjectMapper(
                    albumDelegate = albumDelegate,
                    artistDelegate = artistDelegate)

    @Provides
    @Singleton
    fun interestObjectMapper() = RecommendationInterestObjectMapper()

    @Provides
    @Singleton fun photoObjectMapper(
            processedFileDelegate: RecommendationProcessedFileObjectMapper) =
            RecommendationPhotoObjectMapper(processedFileDelegate)

    @Provides
    @Singleton
    fun jobCompanyObjectMapper() = RecommendationJobCompanyObjectMapper()

    @Provides
    @Singleton
    fun jobTitleObjectMapper() = RecommendationJobTitleObjectMapper()

    @Provides
    @Singleton
    fun jobObjectMapper(
            companyDelegate: RecommendationJobCompanyObjectMapper,
            titleDelegate: RecommendationJobTitleObjectMapper) =
            RecommendationJobObjectMapper(
                    companyDelegate = companyDelegate,
                    titleDelegate = titleDelegate)

    @Provides
    @Singleton
    fun schoolObjectMapper() = RecommendationSchoolObjectMapper()

    @Provides
    @Singleton
    fun responseObjectMapper(
            eventTracker: RecommendationEventTracker,
            commonConnectionDelegate: RecommendationUserCommonConnectionObjectMapper,
            instagramDelegate: RecommendationInstagramObjectMapper,
            teaserDelegate: RecommendationTeaserObjectMapper,
            spotifyThemeTrackDelegate: RecommendationSpotifyThemeTrackObjectMapper,
            commonInterestDelegate: RecommendationInterestObjectMapper,
            photoDelegate: RecommendationPhotoObjectMapper,
            jobDelegate: RecommendationJobObjectMapper,
            schoolDelegate: RecommendationSchoolObjectMapper) =
            RecommendationResponseObjectMapper(
                    eventTracker = eventTracker,
                    commonConnectionDelegate = commonConnectionDelegate,
                    instagramDelegate = instagramDelegate,
                    teaserDelegate = teaserDelegate,
                    spotifyThemeTrackDelegate = spotifyThemeTrackDelegate,
                    commonInterestDelegate = commonInterestDelegate,
                    photoDelegate = photoDelegate,
                    jobDelegate = jobDelegate,
                    schoolDelegate = schoolDelegate)

    @Provides
    @Singleton
    fun facade(
            getRecommendationSource: GetRecommendationSource,
            requestObjectMapper: RecommendationRequestObjectMapper,
            responseObjectMapper: RecommendationResponseObjectMapper) =
            GetRecommendationFacade(getRecommendationSource, requestObjectMapper, responseObjectMapper)
}
