package data.crash

import dagger.Module
import dagger.Provides
import reporter.CrashReporters
import javax.inject.Singleton

@Module
internal class FirebaseCrashReporterModule {
    @Provides
    @Singleton
    fun instance() = CrashReporters.firebase()
}
