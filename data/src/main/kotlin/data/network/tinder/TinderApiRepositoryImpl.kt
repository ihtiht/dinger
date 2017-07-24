package data.network.tinder

import com.google.firebase.crash.FirebaseCrash
import data.ComponentHolder
import data.network.tinder.auth.AuthFacade
import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.repository.TinderApiRepository
import io.reactivex.Single
import javax.inject.Inject

internal class TinderApiRepositoryImpl : TinderApiRepository {
    @Inject
    lateinit var loginFacade: AuthFacade

    init {
          ComponentHolder.authFacadeComponent.inject(this)
    }

    override fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>
            = loginFacade.fetch(parameters).doOnError { FirebaseCrash.report(it) }
}
