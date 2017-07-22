package data.network.tinder.auth

import dagger.Component
import data.network.tinder.TinderApiRepositoryImpl
import javax.inject.Singleton

@Component(modules = arrayOf(AuthFacadeModule::class))
@Singleton
internal interface AuthFacadeComponent {
    fun inject(tinderApiRepositoryImpl: TinderApiRepositoryImpl)
}
