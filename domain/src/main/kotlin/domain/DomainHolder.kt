package domain

import domain.repository.FacadeProvider
import io.reactivex.schedulers.Schedulers

object DomainHolder {
    internal lateinit var facadeProvider: FacadeProvider

    fun facadeProvider(facadeProvider: FacadeProvider) {
        this.facadeProvider = facadeProvider
    }
}
