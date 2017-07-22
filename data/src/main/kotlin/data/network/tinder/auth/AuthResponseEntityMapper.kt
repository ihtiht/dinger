package data.network.tinder.auth

import data.network.common.EntityMapper
import domain.auth.DomainAuthedUser

internal class AuthResponseEntityMapper : EntityMapper<AuthResponse, DomainAuthedUser> {
    override fun map(source: AuthResponse) =
            source.user.let {
                DomainAuthedUser(it.isNewUser, it.apiToken)
            }
}
