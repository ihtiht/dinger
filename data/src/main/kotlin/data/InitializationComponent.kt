package data

import dagger.Component
import data.account.AccountModule
import data.network.NetworkModule
import javax.inject.Singleton

@Component(modules = arrayOf(NetworkModule::class, AccountModule::class))
@Singleton
internal interface InitializationComponent {
    fun inject(initializationContentProvider: InitializationContentProvider)
}
