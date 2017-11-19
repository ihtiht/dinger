package domain.login

interface AccountManagementProvider {
    fun updateOrAddAccount(facebookId: String, facebookToken: String, tinderApiKey: String): Boolean

    fun removeAccount()
}
