package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Module(includes = arrayOf(RecommendationSourceModule::class,
        FirebaseCrashReporterModule::class,
        RecommendationEventTrackerModule::class))
internal class RecommendationFacadeModule {
    @Provides
    @Singleton
    fun requestObjectMapper() = RecommendationRequestObjectMapper()

    @Provides
    @Singleton
    fun responseObjectMapper(
            crashReporter: CrashReporter,
            eventTracker: RecommendationEventTracker) =
            RecommendationResponseObjectMapper(crashReporter, eventTracker)

    @Provides
    @Singleton
    fun facade(
            source: RecommendationSource,
            requestObjectMapper: RecommendationRequestObjectMapper,
            responseObjectMapper: RecommendationResponseObjectMapper) =
            RecommendationFacade(source, requestObjectMapper, responseObjectMapper)
}
