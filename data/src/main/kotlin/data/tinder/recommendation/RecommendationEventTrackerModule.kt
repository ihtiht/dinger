package data.tinder.recommendation

import android.content.Context
import dagger.Module
import dagger.Provides
import data.RootModule
import data.event.FirebaseEventTrackerModule
import tracker.EventTracker
import javax.inject.Singleton

@Module(includes = arrayOf(RootModule::class, FirebaseEventTrackerModule::class))
internal class RecommendationEventTrackerModule constructor() {
    @Provides
    @Singleton
    fun commonConnectionPhotoMarshaller() = CommonConnectionPhotoMarshaller()

    @Provides
    @Singleton
    fun commonConnectionMarshaller(photoDelegate: CommonConnectionPhotoMarshaller) =
            CommonConnectionMarshaller(photoDelegate)

    @Provides
    @Singleton
    fun commonInterestMarshaller() = CommonInterestMarshaller()

    @Provides
    @Singleton
    fun processedFileMarshaller() = ProcessedFileMarshaller()

    @Provides
    @Singleton
    fun userPhotoMarshaller(processedFileDelegate: ProcessedFileMarshaller) =
            UserPhotoMarshaller(processedFileDelegate)

    @Provides
    @Singleton
    fun instagramPhotoMarshaller() = InstagramPhotoMarshaller()

    @Provides
    @Singleton
    fun instagramMarshaller(photoDelegate: InstagramPhotoMarshaller) =
            InstagramMarshaller(photoDelegate)

    @Provides
    @Singleton
    fun jobMarshaller() = JobMarshaller()

    @Provides
    @Singleton
    fun schoolMarshaller() = SchoolMarshaller()

    @Provides
    @Singleton
    fun teaserMarshaller() = TeaserMarshaller()

    @Provides
    @Singleton
    fun albumMarshaller(processedFileDelegate: ProcessedFileMarshaller) =
            SpotifyThemeTrackAlbumMarshaller(processedFileDelegate)

    @Provides
    @Singleton
    fun artistMarshaller() = SpotifyThemeTrackArtistMarshaller()

    @Provides
    @Singleton
    fun spotifyThemeTrack(
            albumDelegate : SpotifyThemeTrackAlbumMarshaller,
            artistDelegate: SpotifyThemeTrackArtistMarshaller) =
            SpotifyThemeTrackMarshaller(albumDelegate, artistDelegate)

    @Provides
    @Singleton
    fun userMarshaller(
            commonConnectionDelegate: CommonConnectionMarshaller,
            commonInterestDelegate: CommonInterestMarshaller,
            photoDelegate: UserPhotoMarshaller,
            instagramDelegate: InstagramMarshaller,
            jobDelegate: JobMarshaller,
            schoolDelegate: SchoolMarshaller,
            teaserDelegate: TeaserMarshaller,
            spotifyThemeTrackDelegate: SpotifyThemeTrackMarshaller) =
            RecommendationUserMarshaller(
                    commonConnectionDelegate = commonConnectionDelegate,
                    commonInterestDelegate = commonInterestDelegate,
                    photoDelegate = photoDelegate,
                    instagramDelegate = instagramDelegate,
                    jobDelegate = jobDelegate,
                    schoolDelegate = schoolDelegate,
                    teaserDelegate = teaserDelegate,
                    spotifyThemeTrackDelegate = spotifyThemeTrackDelegate)

    @Provides
    @Singleton
    fun recommendationMarshaller(userMarshaller: RecommendationUserMarshaller) =
            RecommendationMarshaller(userMarshaller)

    @Provides
    @Singleton
    fun recommendationResponseMarshaller(recommendationMarshaller : RecommendationMarshaller) =
            RecommendationResponseTrackedDataMarshaller(recommendationMarshaller)

    @Provides
    @Singleton
    fun eventTracker(
            context: Context,
            eventTracker: EventTracker,
            recommendationResponseTrackedDataMarshaller: RecommendationResponseTrackedDataMarshaller) =
            RecommendationEventTracker(
                    context,
                    eventTracker = eventTracker,
                    recommendationResponseMarshaller = recommendationResponseTrackedDataMarshaller)
}
