package data

import dagger.Component
import data.account.AccountModule
import data.alarm.AlarmModule
import data.autoswipe.AutoSwipeModule
import data.network.NetworkModule
import javax.inject.Singleton

@Component(modules = arrayOf(
        NetworkModule::class,
        AccountModule::class,
        AlarmModule::class,
        AutoSwipeModule::class))
@Singleton
internal interface InitializationComponent {
    fun inject(initializationContentProvider: InitializationContentProvider)
}
