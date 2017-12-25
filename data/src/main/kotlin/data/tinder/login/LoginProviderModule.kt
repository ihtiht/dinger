package data.tinder.login

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.login.LoginProvider
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [LoginFacadeModule::class, FirebaseCrashReporterModule::class])
internal class LoginProviderModule {
    @Provides
    @Singleton
    fun loginProvider(loginFacade: LoginFacade,
                      crashReporter: CrashReporter): LoginProvider =
            LoginProviderImpl(loginFacade, crashReporter)
}
