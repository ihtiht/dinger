package data.tinder.auth

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Module(includes = arrayOf(AuthSourceModule::class))
internal class AuthFacadeModule {
    @Provides
    @Singleton
    fun requestObjectMapper() = AuthRequestObjectMapper()

    @Provides
    @Singleton
    fun responseObjectMapper() = AuthResponseObjectMapper()

    @Provides
    @Singleton
    fun facade(
            source: AuthSource,
            requestObjectMapper: AuthRequestObjectMapper,
            responseObjectMapper: AuthResponseObjectMapper) =
            AuthFacade(source, requestObjectMapper, responseObjectMapper)
}
