package data

import data.account.AccountComponent
import data.network.tinder.TinderRepositoryComponent

internal object ComponentHolder {
    lateinit var accountComponent: AccountComponent
    lateinit var tinderRepositoryComponent: TinderRepositoryComponent
}
