package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import domain.recommendation.GetRecommendation
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [GetRecommendationFacadeModule::class, FirebaseCrashReporterModule::class])
internal class GetRecommendationModule {
    @Provides
    @Singleton
    fun getRecommendation(getRecommendationFacade: GetRecommendationFacade,
                          crashReporter: CrashReporter): GetRecommendation =
            GetRecommendationImpl(getRecommendationFacade, crashReporter)
}
