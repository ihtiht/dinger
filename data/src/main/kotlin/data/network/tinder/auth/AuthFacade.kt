package data.network.tinder.auth

import data.network.common.EntityMapper
import data.network.common.RequestFacade
import data.network.common.RequestSource
import domain.auth.DomainAuthedUser
import javax.inject.Inject

internal class AuthFacade @Inject constructor(
        source: RequestSource<AuthRequestParameters, AuthResponse>,
        entityMapper: EntityMapper<AuthResponse, DomainAuthedUser>)
    : RequestFacade<AuthRequestParameters, AuthResponse, DomainAuthedUser>(source, entityMapper)
