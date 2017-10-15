package app.event

import dagger.Module
import dagger.Provides
import tracker.EventTracker
import tracker.EventTrackers
import javax.inject.Singleton

@Module
internal class FirebaseEventTrackerModule {
    @Provides
    @Singleton
    fun instance(): EventTracker = EventTrackers.firebase()
}
