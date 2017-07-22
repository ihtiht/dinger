package data.network.tinder

import data.ComponentHolder
import data.network.tinder.auth.AuthFacade
import domain.auth.DomainAuthRequestParameters
import domain.repository.TinderApiRepository
import javax.inject.Inject

internal class TinderApiRepositoryImpl : TinderApiRepository {
    @Inject
    lateinit var loginFacade: AuthFacade

    init {
          ComponentHolder.authFacadeComponent.inject(this)
    }

    override fun login(parameters: DomainAuthRequestParameters) = loginFacade.fetch(parameters)
}
