package domain

import domain.repository.DomainFacadeProvider
import io.reactivex.schedulers.Schedulers

/**
 * Global configuration holder for the module.
 * Note how this class acts as a dependency holder. You could also a DI framework like Dagger for
 * example, but to only provide a single dependency, which is also a singleton, might as well do it
 * myself instead.
 */
object Domain {
    internal lateinit var facadeProvider: DomainFacadeProvider
    internal val useCaseScheduler = Schedulers.io()

    /**
     * Set a facade provider.
     * @param facade The facadeProvider to set.
     */
    fun facadeProvider(facade: DomainFacadeProvider) {
        this.facadeProvider = facade
    }
}
