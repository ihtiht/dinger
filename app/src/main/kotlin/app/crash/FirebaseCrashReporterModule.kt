package app.crash

import dagger.Module
import dagger.Provides
import reporter.CrashReporter
import javax.inject.Singleton

@Module
internal class FirebaseCrashReporterModule {
    @Provides
    @Singleton
    fun instance(): CrashReporter = CrashReporter.firebase()
}
