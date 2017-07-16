package data.network.tinder.auth

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import data.network.common.RequestSource
import domain.auth.DomainAuthedUser
import javax.inject.Inject

internal class AuthFacade @Inject constructor(
        source: RequestSource<AuthRequest, AuthResponse>,
        entityMapper: EntityMapper<AuthResponse, DomainAuthedUser>)
    : RequestFacade<AuthRequest, AuthResponse, DomainAuthedUser>(source, entityMapper)
