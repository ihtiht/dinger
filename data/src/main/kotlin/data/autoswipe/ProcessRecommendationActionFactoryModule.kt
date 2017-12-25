package data.autoswipe

import dagger.Module
import dagger.Provides
import data.tinder.dislike.DislikeRecommendationActionFactoryModule
import data.tinder.dislike.DislikeRecommendationActionFactoryWrapper
import data.tinder.like.LikeRecommendationActionFactoryModule
import data.tinder.like.LikeRecommendationActionFactoryWrapper
import javax.inject.Singleton

@Module(includes = [
    DislikeRecommendationActionFactoryModule::class,
    LikeRecommendationActionFactoryModule::class])
internal class ProcessRecommendationActionFactoryModule {
    @Provides
    @Singleton
    fun processRecommendationActionFactory(
            likeRecommendationActionFactory: dagger.Lazy<LikeRecommendationActionFactoryWrapper>,
            dislikeRecommendationActionFactory
            : dagger.Lazy<DislikeRecommendationActionFactoryWrapper>) =
            ProcessRecommendationActionFactoryWrapper {
        ProcessRecommendationAction(
                it, likeRecommendationActionFactory, dislikeRecommendationActionFactory)
    }
}
