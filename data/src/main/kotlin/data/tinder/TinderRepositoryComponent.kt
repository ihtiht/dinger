package data.tinder

import dagger.Component
import data.crash.FirebaseCrashReporterModule
import data.tinder.auth.AuthFacadeModule
import data.tinder.like.LikeFacadeModule
import data.tinder.recommendation.RecommendationFacadeModule
import javax.inject.Singleton

@Component(modules = arrayOf(
        AuthFacadeModule::class,
        RecommendationFacadeModule::class,
        LikeFacadeModule::class,
        FirebaseCrashReporterModule::class))
@Singleton
internal interface TinderRepositoryComponent {
    fun inject(tinderApiRepositoryImpl: TinderApiRepositoryImpl)
}
