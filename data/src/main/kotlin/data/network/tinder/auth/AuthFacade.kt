package data.network.tinder.auth

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import domain.auth.DomainAuthedUser

internal class AuthFacade constructor(
        source: AuthSource,
        entityMapper: EntityMapper<AuthResponse, DomainAuthedUser>)
    : RequestFacade<AuthRequestParameters, AuthResponse, DomainAuthedUser>(source, entityMapper)
