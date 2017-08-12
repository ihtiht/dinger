package data.tinder.auth

import data.network.EntityMapper
import domain.auth.DomainAuthRequestParameters

internal class AuthRequestEntityMapper
    : EntityMapper<DomainAuthRequestParameters, AuthRequestParameters> {
    override fun transform(source: DomainAuthRequestParameters) =
            source.let {
                AuthRequestParameters(it.facebookId, it.facebookToken)
            }
}
