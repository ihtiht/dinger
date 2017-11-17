package data.stoyicker.versioncheck

import dagger.Module
import dagger.Provides
import data.tinder.login.LoginFacade
import data.tinder.login.LoginRequestObjectMapper
import data.tinder.login.LoginResponseObjectMapper
import data.tinder.login.LoginSource
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Module(includes = arrayOf(VersionCheckSourceModule::class))
internal class VersionCheckFacadeModule {
    @Provides
    @Singleton
    fun responseObjectMapper() = LoginResponseObjectMapper()

    @Provides
    @Singleton
    fun facade(source: VersionCheckSource,
               responseObjectMapper: VersionResponseObjectMapper) =
            VersionCheckFacade(source, responseObjectMapper)
}
