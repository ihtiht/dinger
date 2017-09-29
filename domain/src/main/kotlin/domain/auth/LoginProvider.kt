package domain.auth

import io.reactivex.Single

interface LoginProvider {
    fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>
}
