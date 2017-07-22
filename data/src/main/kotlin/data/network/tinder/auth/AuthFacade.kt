package data.network.tinder.auth

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import domain.auth.DomainAuthRequestParameters
import domain.auth.DomainAuthedUser

internal class AuthFacade constructor(
        source: AuthSource,
        requestMapper: EntityMapper<DomainAuthRequestParameters, AuthRequestParameters>,
        responseMapper: EntityMapper<AuthResponse, DomainAuthedUser>)
    : RequestFacade<
        DomainAuthRequestParameters, AuthRequestParameters, AuthResponse, DomainAuthedUser>(
        source, requestMapper, responseMapper)
