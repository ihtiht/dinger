package data.tinder.recommendation

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

/**
 * Module used to provide stuff required by TopRequestFacade objects.
 */
@Module(includes = arrayOf(RecommendationSourceModule::class, FirebaseCrashReporterModule::class))
internal class RecommendationFacadeModule {
    @Provides
    @Singleton
    fun requestEntityMapper() = RecommendationRequestEntityMapper()

    @Provides
    @Singleton
    fun responseEntityMapper(crashReporter: CrashReporter) = RecommendationResponseEntityMapper(
            crashReporter)

    @Provides
    @Singleton
    fun facade(
            source: RecommendationSource,
            requestEntityMapper: RecommendationRequestEntityMapper,
            responseEntityMapper: RecommendationResponseEntityMapper) =
            RecommendationFacade(source, requestEntityMapper, responseEntityMapper)
}
