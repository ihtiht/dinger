package data

import data.account.AccountComponent
import data.network.tinder.auth.AuthFacadeComponent

internal object ComponentHolder {
    lateinit var accountComponent: AccountComponent
    lateinit var authFacadeComponent: AuthFacadeComponent
}
