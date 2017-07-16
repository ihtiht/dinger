package data.tinder.auth

import data.common.EntityMapper
import data.common.RequestFacade
import data.common.RequestSource
import domain.auth.DomainAuthedUser
import javax.inject.Inject

internal class AuthFacade @Inject constructor(
        source: RequestSource<AuthRequest, AuthResponse>,
        entityMapper: EntityMapper<AuthResponse, DomainAuthedUser>)
    : RequestFacade<AuthRequest, AuthResponse, DomainAuthedUser>(source, entityMapper)
