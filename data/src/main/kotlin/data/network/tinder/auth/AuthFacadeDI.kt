package data.network.tinder.auth

import com.nytimes.android.external.store3.base.impl.Store
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Component(modules = arrayOf(AuthSourceModule::class, AuthFacadeModule::class))
@Singleton
internal interface AuthFacadeComponent

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
    fun topRequestSource(store: DaggerLazy<Store<AuthResponse, AuthRequest>>) = AuthSource(store)
}
