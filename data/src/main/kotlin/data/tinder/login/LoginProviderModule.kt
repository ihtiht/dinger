package data.tinder.login

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(LoginFacadeModule::class, FirebaseCrashReporterModule::class))
internal class LoginProviderModule {
    @Provides
    @Singleton
    fun loginProvider(loginFacade: LoginFacade,
                      crashReporter: CrashReporter): LoginProviderImpl =
            LoginProviderImpl(loginFacade, crashReporter)
}
