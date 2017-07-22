package data.network.tinder.auth

import dagger.Component
import dagger.Module
import dagger.Provides
import data.network.tinder.TinderApiRepositoryImpl
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Component(modules = arrayOf(AuthSourceModule::class, AuthFacadeModule::class))
@Singleton
internal interface AuthFacadeComponent {
    fun inject(tinderApiRepositoryImpl: TinderApiRepositoryImpl)
}

/**
 * Module used to provide stuff required by TopRequestFacade objects.
 */
@Module
internal class AuthFacadeModule {
    @Provides
    @Singleton
    fun entityMapper() = AuthEntityMapper()

    @Provides
    @Singleton
    fun facade(source: AuthSource, entityMapper: AuthEntityMapper)
            = AuthFacade(source, entityMapper)
}
