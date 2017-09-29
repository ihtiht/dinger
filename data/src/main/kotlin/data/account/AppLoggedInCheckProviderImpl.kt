package data.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import org.stoyicker.dinger.data.R
import domain.loggedincheck.LoggedInCheckProvider as AppAccountManager

internal class AppLoggedInCheckProviderImpl constructor(context: Context)
    : AppAccountManager, AbstractAccountAuthenticator(context) {
    init {
        ACCOUNT_TYPE = context.getString(R.string.account_type)
    }

    private val delegate by lazy { AccountManager.get(context) }

    override fun addAccount(
            p0: AccountAuthenticatorResponse?,
            p1: String?,
            p2: String?,
            p3: Array<out String>?,
            p4: Bundle?) = throw UnsupportedOperationException("Not supported")

    override fun getAuthTokenLabel(p0: String?) =
            throw UnsupportedOperationException("Not supported")

    override fun confirmCredentials(
            p0: AccountAuthenticatorResponse?,
            p1: Account?,
            p2: Bundle?) = throw UnsupportedOperationException("Not supported")

    override fun updateCredentials(
            p0: AccountAuthenticatorResponse?,
            p1: Account?,
            p2: String?,
            p3: Bundle?) = throw UnsupportedOperationException("Not supported")

    override fun getAuthToken(
            p0: AccountAuthenticatorResponse?,
            p1: Account?,
            p2: String?,
            p3: Bundle?) = throw UnsupportedOperationException("Not supported")

    override fun hasFeatures(
            p0: AccountAuthenticatorResponse?,
            p1: Account?,
            p2: Array<out String>?) = throw UnsupportedOperationException("Not supported")

    override fun editProperties(p0: AccountAuthenticatorResponse?, p1: String?) =
            throw UnsupportedOperationException("Not supported")

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

    private companion object {
        lateinit var ACCOUNT_TYPE: String
    }
}
