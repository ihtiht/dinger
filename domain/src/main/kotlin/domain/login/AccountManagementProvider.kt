package domain.login

interface AccountManagementProvider {
    fun addAccount(facebookId: String, facebookToken: String, tinderApiKey: String): Boolean

    fun removeAccount()
}
