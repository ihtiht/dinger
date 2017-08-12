package data.tinder.auth

import data.network.EntityMapper
import domain.auth.DomainAuthedUser

internal class AuthResponseEntityMapper : EntityMapper<AuthResponse, DomainAuthedUser> {
    override fun transform(source: AuthResponse) = source.data.let { DomainAuthedUser(it.apiToken) }

}
