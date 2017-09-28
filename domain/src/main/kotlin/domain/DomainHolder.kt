package domain

import domain.repository.FacadeProvider

object DomainHolder {
    internal lateinit var facadeProvider: FacadeProvider

    fun facadeProvider(facadeProvider: FacadeProvider) {
        this.facadeProvider = facadeProvider
    }
}
