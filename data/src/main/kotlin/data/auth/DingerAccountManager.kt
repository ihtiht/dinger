package data.auth

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import org.stoyicker.dinger.data.BuildConfig
import domain.auth.AccountManager as AppAccountManager

internal object DingerAccountManager : AppAccountManager {
    lateinit var context: Context
    private val delegate by lazy { AccountManager.get(context) }

    override fun addAccount(id: String, token: String) = Account(id, Companion.ACCOUNT_TYPE).let {
        if (delegate.addAccountExplicitly(it, token, null)) {
            delegate.notifyAccountAuthenticated(it)
            true
        } else {
            false
        }
    }

    override fun isThereALoggedInUser() = getAccountToken() != null

    internal fun getAccountToken() = delegate.getAccountsByType(Companion.ACCOUNT_TYPE).let {
        when (it.size) {
            0 -> null
            else -> delegate.getPassword(it.first())
        }
    }

    private object Companion {
        const val ACCOUNT_TYPE: String = BuildConfig.APPLICATION_ID
    }
}
