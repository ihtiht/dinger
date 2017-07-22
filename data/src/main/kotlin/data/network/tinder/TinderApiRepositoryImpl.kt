package data.network.tinder

import data.network.tinder.auth.AuthFacade
import data.network.tinder.auth.DaggerAuthFacadeComponent
import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.repository.TinderApiRepository
import io.reactivex.Single
import javax.inject.Inject

internal class TinderApiRepositoryImpl : TinderApiRepository {
    @Inject
    lateinit var loginFacade: AuthFacade

    init {
        DaggerAuthFacadeComponent.create().inject(this)
    }

    override fun login(parameters: DomainAuthRequestParameters) = loginFacade.fetch(parameters)
}
