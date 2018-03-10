package data.tinder.login

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.login.Login
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [LoginFacadeModule::class, FirebaseCrashReporterModule::class])
internal class LoginModule {
    @Provides
    @Singleton
    fun login(loginFacade: LoginFacade, crashReporter: CrashReporter): Login =
            LoginImpl(loginFacade, crashReporter)
}
