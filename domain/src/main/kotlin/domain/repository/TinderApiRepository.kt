package domain.repository

import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser
import io.reactivex.Single

interface TinderApiRepository {
    fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>
}
