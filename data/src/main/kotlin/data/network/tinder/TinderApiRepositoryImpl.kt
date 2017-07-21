package data.network.tinder

import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import domain.repository.TinderApiRepository
import io.reactivex.Single

internal object TinderApiRepositoryImpl : TinderApiRepository {
    override fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
