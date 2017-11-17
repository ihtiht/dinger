package data.stoyicker.versioncheck

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(VersionCheckFacadeModule::class, FirebaseCrashReporterModule::class))
internal class VersionCheckProviderModule {
    @Provides
    @Singleton
    fun versionCheckProvider(versionCheckFacade: VersionCheckFacade,
                             crashReporter: CrashReporter): VersionCheckProviderImpl =
            VersionCheckProviderImpl(versionCheckFacade, crashReporter)
}
