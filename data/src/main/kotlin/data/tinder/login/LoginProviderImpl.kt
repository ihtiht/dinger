package data.tinder.login

import domain.login.DomainAuthRequestParameters
import domain.login.DomainAuthenticatedUser
import domain.login.LoginProvider
import io.reactivex.Single
import reporter.CrashReporter

internal class LoginProviderImpl(
        private val loginFacade: LoginFacade,
        private val crashReporter: CrashReporter) : LoginProvider {
    override fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthenticatedUser> =
            loginFacade.fetch(parameters).doOnError { crashReporter.report(it) }
}
