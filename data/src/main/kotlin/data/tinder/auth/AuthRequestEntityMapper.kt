package data.tinder.auth

import data.EntityMapper
import domain.auth.DomainAuthRequestParameters

internal class AuthRequestEntityMapper
    : EntityMapper<DomainAuthRequestParameters, AuthRequestParameters> {
    override fun from(source: DomainAuthRequestParameters) =
            source.let {
                AuthRequestParameters(it.facebookId, it.facebookToken)
            }
}
