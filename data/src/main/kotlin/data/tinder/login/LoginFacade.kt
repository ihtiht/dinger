package data.tinder.login

import data.ObjectMapper
import data.network.RequestFacade
import domain.login.DomainAuthRequestParameters
import domain.login.DomainAuthedUser

internal class LoginFacade(
        source: LoginSource,
        requestMapper: ObjectMapper<DomainAuthRequestParameters, LoginRequestParameters>,
        responseMapper: ObjectMapper<LoginResponse, DomainAuthedUser>)
    : RequestFacade<
        DomainAuthRequestParameters, LoginRequestParameters, LoginResponse, DomainAuthedUser>(
        source, requestMapper, responseMapper)
