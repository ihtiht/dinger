package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(RecommendationFacadeModule::class, FirebaseCrashReporterModule::class))
internal class GetRecommendationProviderModule {
    @Provides
    @Singleton
    fun recommendationProvider(recommendationFacade: RecommendationFacade,
                               crashReporter: CrashReporter): GetRecommendationProviderImpl =
            GetRecommendationProviderImpl(recommendationFacade, crashReporter)
}
