package app.crash

import dagger.Module
import dagger.Provides
import reporter.CrashReporter
import reporter.CrashReporters
import javax.inject.Singleton

@Module
internal class FirebaseCrashReporterModule {
    @Provides
    @Singleton
    fun instance(): CrashReporter = CrashReporters.firebase()
}
