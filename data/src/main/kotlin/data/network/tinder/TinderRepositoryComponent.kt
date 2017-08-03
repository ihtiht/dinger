package data.network.tinder

import dagger.Component
import data.network.tinder.auth.AuthFacadeModule
import data.network.tinder.recommendation.RecommendationFacadeModule
import javax.inject.Singleton

@Component(modules = arrayOf(AuthFacadeModule::class, RecommendationFacadeModule::class))
@Singleton
internal interface TinderRepositoryComponent {
    fun inject(tinderApiRepositoryImpl: TinderApiRepositoryImpl)
}
