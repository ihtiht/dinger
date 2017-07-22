package data.network.tinder.auth

import data.network.common.EntityMapper
import domain.auth.DomainAuthRequestParameters

internal class AuthRequestEntityMapper
    : EntityMapper<DomainAuthRequestParameters, AuthRequestParameters> {
    override fun transform(source: DomainAuthRequestParameters) =
            source.let {
                AuthRequestParameters(it.facebookId, it.facebookToken)
            }
}
