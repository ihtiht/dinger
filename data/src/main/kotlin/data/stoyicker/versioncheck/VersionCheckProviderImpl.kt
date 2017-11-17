package data.stoyicker.versioncheck

import domain.versioncheck.DomainVersionCheckResponse
import domain.versioncheck.VersionCheckProvider
import io.reactivex.Single
import reporter.CrashReporter

internal class VersionCheckProviderImpl(
        private val versionCheckFacade: VersionCheckFacade,
        private val crashReporter: CrashReporter) : VersionCheckProvider {
    override fun versionCheck(): Single<DomainVersionCheckResponse> =
            versionCheckFacade.fetch(Unit).doOnError { crashReporter.report(it) }
}
