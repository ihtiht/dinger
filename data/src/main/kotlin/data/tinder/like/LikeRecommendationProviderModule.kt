package data.tinder.like

import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = arrayOf(LikeFacadeModule::class, FirebaseCrashReporterModule::class))
internal class LikeRecommendationProviderModule {
    @Provides
    @Singleton
    fun likeRecommendationProvider(likeFacade: LikeFacade, crashReporter: CrashReporter)
            : LikeRecommendationProviderImpl =
            LikeRecommendationProviderImpl(likeFacade, crashReporter)
}
