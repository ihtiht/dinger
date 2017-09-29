package data.tinder.auth

import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.auth.LoginProvider
import io.reactivex.Single
import reporter.CrashReporter

internal class LoginProviderImpl(
        private val loginFacade: AuthFacade,
        private val crashReporter: CrashReporter) : LoginProvider {
    override fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser> =
            loginFacade.fetch(parameters).doOnError { crashReporter.report(it) }
}
