package data.tinder.auth

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

/**
 * Module used to provide stuff required by TopRequestFacade objects.
 */
@Module(includes = arrayOf(AuthSourceModule::class))
internal class AuthFacadeModule {
    @Provides
    @Singleton
    fun requestEntityMapper() = AuthRequestEntityMapper()

    @Provides
    @Singleton
    fun responseEntityMapper() = AuthResponseEntityMapper()

    @Provides
    @Singleton
    fun facade(
            source: AuthSource,
            requestEntityMapper: AuthRequestEntityMapper,
            responseEntityMapper: AuthResponseEntityMapper)
            = AuthFacade(source, requestEntityMapper, responseEntityMapper)
}
