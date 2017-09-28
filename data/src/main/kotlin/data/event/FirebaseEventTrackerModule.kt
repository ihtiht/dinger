package data.event

import dagger.Module
import dagger.Provides
import tracker.EventTrackers
import javax.inject.Singleton

@Module
internal class FirebaseEventTrackerModule {
    @Provides
    @Singleton
    fun instance() = EventTrackers.firebase()
}
