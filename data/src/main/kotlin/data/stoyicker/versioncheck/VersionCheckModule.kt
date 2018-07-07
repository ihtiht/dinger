package data.stoyicker.versioncheck

import dagger.Module
import dagger.Provides
import data.crash.VoidCrashReporterModule
import domain.versioncheck.VersionCheck
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [VersionCheckFacadeModule::class, VoidCrashReporterModule::class])
internal class VersionCheckModule {
    @Provides
    @Singleton
    fun versionCheck(versionCheckFacade: VersionCheckFacade,
                     crashReporter: CrashReporter): VersionCheck =
            VersionCheckImpl(versionCheckFacade, crashReporter)
}
