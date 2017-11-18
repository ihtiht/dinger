package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.recommendation.GetRecommendationProvider
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(GetRecommendationFacadeModule::class, FirebaseCrashReporterModule::class))
internal class GetRecommendationProviderModule {
    @Provides
    @Singleton
    fun getRecommendationProvider(getRecommendationFacade: GetRecommendationFacade,
                                  crashReporter: CrashReporter): GetRecommendationProvider =
            GetRecommendationProviderImpl(getRecommendationFacade, crashReporter)
}
