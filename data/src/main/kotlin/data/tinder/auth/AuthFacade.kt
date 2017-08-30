package data.tinder.auth

import data.ObjectMapper
import data.network.RequestFacade
import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser

internal class AuthFacade constructor(
        source: AuthSource,
        requestMapper: ObjectMapper<DomainAuthRequestParameters, AuthRequestParameters>,
        responseMapper: ObjectMapper<AuthResponse, DomainAuthedUser>)
    : RequestFacade<
        DomainAuthRequestParameters, AuthRequestParameters, AuthResponse, DomainAuthedUser>(
        source, requestMapper, responseMapper)
