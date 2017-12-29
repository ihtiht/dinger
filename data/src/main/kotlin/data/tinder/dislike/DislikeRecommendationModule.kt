package data.tinder.dislike

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.dislike.DislikeRecommendation
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [DislikeFacadeModule::class, FirebaseCrashReporterModule::class])
internal class DislikeRecommendationModule {
    @Provides
    @Singleton
    fun dislikeRecommendation(dislikeFacade: DislikeFacade, crashReporter: CrashReporter)
            : DislikeRecommendation =
            DislikeRecommendationImpl(dislikeFacade, crashReporter)
}
