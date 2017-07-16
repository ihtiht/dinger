package data.tinder.auth

import data.common.EntityMapper
import domain.auth.DomainAuthedUser

internal class AuthEntityMapper : EntityMapper<AuthResponse, DomainAuthedUser> {
    override fun map(source: AuthResponse) =
            source.user.let {
                DomainAuthedUser(it.isNewUser, it.apiToken)
            }
}
