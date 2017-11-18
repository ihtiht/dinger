package data.stoyicker.versioncheck

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.versioncheck.VersionCheckProvider
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(VersionCheckFacadeModule::class, FirebaseCrashReporterModule::class))
internal class VersionCheckProviderModule {
    @Provides
    @Singleton
    fun versionCheckProvider(versionCheckFacade: VersionCheckFacade,
                             crashReporter: CrashReporter): VersionCheckProvider =
            VersionCheckProviderImpl(versionCheckFacade, crashReporter)
}
