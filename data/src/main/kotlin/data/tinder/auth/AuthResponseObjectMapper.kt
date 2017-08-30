package data.tinder.auth

import data.ObjectMapper
import domain.auth.DomainAuthedUser

internal class AuthResponseObjectMapper : ObjectMapper<AuthResponse, DomainAuthedUser> {
    override fun from(source: AuthResponse) = source.data.let { DomainAuthedUser(it.apiToken) }
}
