package domain

import domain.auth.AccountManager
import domain.repository.FacadeProvider
import io.reactivex.schedulers.Schedulers

/**
 * Global configuration holder for the module.
 * Note how this class acts as a dependency holder. You could also a DI framework like Dagger for
 * example, but to only provide a single dependency, which is also a singleton, might as well do it
 * myself instead.
 */
object Domain {
    internal lateinit var facadeProvider: FacadeProvider
    internal lateinit var accountManager: AccountManager
    internal val useCaseScheduler = Schedulers.io()

    fun facadeProvider(facadeProvider: FacadeProvider) {
        this.facadeProvider = facadeProvider
    }

    fun accountManager(accountManager: AccountManager){
        this.accountManager = accountManager
    }
}
