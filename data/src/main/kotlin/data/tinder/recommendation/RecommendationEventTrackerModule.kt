package data.tinder.recommendation

import android.content.Context
import dagger.Module
import dagger.Provides
import data.RootModule
import data.event.FirebaseEventTrackerModule
import tracker.EventTracker
import javax.inject.Singleton

@Module(includes = [RootModule::class, FirebaseEventTrackerModule::class])
internal class RecommendationEventTrackerModule {
    @Provides
    @Singleton
    fun recommendationResponseMarshaller() = RecommendationResponseTrackedDataMarshaller()

    @Provides
    @Singleton
    fun eventTracker(
            context: Context,
            eventTracker: EventTracker,
            recommendationResponseTrackedDataMarshaller: RecommendationResponseTrackedDataMarshaller) =
            RecommendationEventTracker(
                    context,
                    eventTracker,
                    recommendationResponseTrackedDataMarshaller)
}
