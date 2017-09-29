package data.tinder.login

import data.ObjectMapper
import domain.login.DomainAuthedUser

internal class LoginResponseObjectMapper : ObjectMapper<LoginResponse, DomainAuthedUser> {
    override fun from(source: LoginResponse) = source.data.let { DomainAuthedUser(it.apiToken) }
}
