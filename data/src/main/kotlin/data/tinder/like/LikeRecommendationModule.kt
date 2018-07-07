package data.tinder.like

import dagger.Module
import dagger.Provides
import data.crash.VoidCrashReporterModule
import domain.like.LikeRecommendation
import reporter.CrashReporter
import javax.inject.Singleton

@Module(includes = [LikeFacadeModule::class, VoidCrashReporterModule::class])
internal class LikeRecommendationModule {
    @Provides
    @Singleton
    fun likeRecommendation(likeFacade: LikeFacade, crashReporter: CrashReporter)
            : LikeRecommendation = LikeRecommendationImpl(likeFacade, crashReporter)
}
