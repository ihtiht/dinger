package data.crash

import dagger.Module
import dagger.Provides
import reporter.CrashReporters
import javax.inject.Singleton

@Module
internal class VoidCrashReporterModule {
    @Provides
    @Singleton
    fun instance() = CrashReporters.void()
}
