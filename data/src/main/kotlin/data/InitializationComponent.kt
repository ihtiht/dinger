package data

import dagger.Component
import data.account.AccountModule
import data.alarm.AlarmModule
import data.autoswipe.AutoSwipeModule
import data.network.NetworkModule
import data.stoyicker.versioncheck.VersionCheckProviderModule
import data.tinder.like.LikeRecommendationProviderModule
import data.tinder.login.LoginProviderModule
import data.tinder.recommendation.GetRecommendationProviderModule
import javax.inject.Singleton

@Component(modules = arrayOf(
        NetworkModule::class,
        LoginProviderModule::class,
        GetRecommendationProviderModule::class,
        LikeRecommendationProviderModule::class,
        AccountModule::class,
        AlarmModule::class,
        AutoSwipeModule::class,
        VersionCheckProviderModule::class))
@Singleton
internal interface InitializationComponent {
    fun inject(initializationContentProvider: InitializationContentProvider)
}
