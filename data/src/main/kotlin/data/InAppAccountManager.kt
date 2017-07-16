package data

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context

object InAppAccountManager {
    lateinit var context: Context
    private val delegate by lazy { AccountManager.get(context) }

    fun addAccount(token: String) =
        Account(token, Companion.ACCOUNT_TYPE).let {
            if (delegate.addAccountExplicitly(it, null, null)) {
                delegate.notifyAccountAuthenticated(it)
                true
            } else {
                false
            }
        }

    fun getAccountToken() =
            delegate.getAccountsByType(Companion.ACCOUNT_TYPE).let {
                when (it.size) {
                    0 -> null
                    else -> delegate.getPassword(it.first())
                }
            }

    private object Companion {
        const val ACCOUNT_TYPE = "DINGER"
    }
}
