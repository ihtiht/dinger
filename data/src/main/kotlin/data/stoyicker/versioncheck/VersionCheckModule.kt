package data.stoyicker.versioncheck

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.versioncheck.VersionCheck
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [VersionCheckFacadeModule::class, FirebaseCrashReporterModule::class])
internal class VersionCheckModule {
    @Provides
    @Singleton
    fun versionCheck(versionCheckFacade: VersionCheckFacade,
                     crashReporter: CrashReporter): VersionCheck =
            VersionCheckImpl(versionCheckFacade, crashReporter)
}
