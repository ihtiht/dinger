package data.tinder.auth

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(AuthFacadeModule::class, FirebaseCrashReporterModule::class))
internal class LoginProviderModule {
    @Provides
    @Singleton
    fun loginProvider(authFacade: AuthFacade,
                      crashReporter: CrashReporter): LoginProviderImpl =
            LoginProviderImpl(authFacade, crashReporter)
}
