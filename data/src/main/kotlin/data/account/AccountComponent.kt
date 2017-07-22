package data.account

import dagger.Component
import data.InitializationContentProvider
import javax.inject.Singleton

@Component(modules = arrayOf(AccountModule::class))
@Singleton
internal interface AccountComponent {
    fun inject(initializationContentProvider: InitializationContentProvider)

    fun inject(dingerAccountManagerService: DingerAccountManagerService)
}
