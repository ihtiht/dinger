package data.tinder.auth

import data.ObjectMapper
import domain.auth.DomainAuthRequestParameters

internal class AuthRequestObjectMapper
    : ObjectMapper<DomainAuthRequestParameters, AuthRequestParameters> {
    override fun from(source: DomainAuthRequestParameters) =
            source.let {
                AuthRequestParameters(it.facebookId, it.facebookToken)
            }
}
