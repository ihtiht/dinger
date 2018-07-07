package data.tinder.login

import dagger.Module
import dagger.Provides
import data.crash.VoidCrashReporterModule
import domain.login.Login
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [LoginFacadeModule::class, VoidCrashReporterModule::class])
internal class LoginModule {
    @Provides
    @Singleton
    fun login(loginFacade: LoginFacade, crashReporter: CrashReporter): Login =
            LoginImpl(loginFacade, crashReporter)
}
