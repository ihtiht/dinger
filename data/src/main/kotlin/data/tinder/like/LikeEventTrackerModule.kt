package data.tinder.like

import android.content.Context
import dagger.Module
import dagger.Provides
import data.RootModule
import data.event.FirebaseEventTrackerModule
import tracker.EventTracker
import javax.inject.Singleton

@Module(includes = [RootModule::class, FirebaseEventTrackerModule::class])
internal class LikeEventTrackerModule {
    @Provides
    @Singleton
    fun likeResponseMarshaller() = LikeResponseTrackedDataMarshaller()

    @Provides
    @Singleton
    fun eventTracker(
            context: Context,
            eventTracker: EventTracker,
            likeResponseMarshaller: LikeResponseTrackedDataMarshaller) =
            LikeEventTracker(
                    context,
                    eventTracker,
                    likeResponseMarshaller)
}
