package domain.login

object LoginHolder {
    internal lateinit var loginProvider: LoginProvider
    internal lateinit var accountManagementProvider: AccountManagementProvider

    fun loginProvider(it: LoginProvider) {
        loginProvider = it
    }

    fun addAccountProvider(it: AccountManagementProvider) {
        accountManagementProvider = it
    }
}
