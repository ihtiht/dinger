package data.tinder.dislike

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.dislike.DislikeRecommendationProvider
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [DislikeFacadeModule::class, FirebaseCrashReporterModule::class])
internal class DislikeRecommendationProviderModule {
    @Provides
    @Singleton
    fun dislikeRecommendationProvider(dislikeFacade: DislikeFacade, crashReporter: CrashReporter)
            : DislikeRecommendationProvider =
            DislikeRecommendationProviderImpl(dislikeFacade, crashReporter)
}
