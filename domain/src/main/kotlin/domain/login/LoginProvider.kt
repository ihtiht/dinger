package domain.login

import io.reactivex.Single

interface LoginProvider {
    fun login(parameters: DomainAuthRequestParameters): Single<DomainAuthedUser>
}
