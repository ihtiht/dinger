package data.autoswipe

import dagger.Component
import data.crash.FirebaseCrashReporterModule
import data.tinder.recommendation.GetRecommendationsActionModule
import data.tinder.recommendation.RecommendationUserResolverModule
import javax.inject.Singleton

@Component(modules = [
    AutoSwipeReportHandlerModule::class,
    FirebaseCrashReporterModule::class,
    GetRecommendationsActionModule::class,
    ProcessRecommendationActionFactoryModule::class,
    RecommendationUserResolverModule::class])
@Singleton
internal interface AutoSwipeComponent {
    fun inject(autoSwipeJobIntentService: AutoSwipeJobIntentService)
}
