package data.autoswipe

import dagger.Component
import data.crash.FirebaseCrashReporterModule
import data.tinder.recommendation.RecommendationUserResolverModule
import javax.inject.Singleton

@Component(modules = arrayOf(
        FirebaseCrashReporterModule::class, RecommendationUserResolverModule::class))
@Singleton
internal interface AutoSwipeComponent {
    fun inject(autoSwipeJobIntentService: AutoSwipeJobIntentService)
}
