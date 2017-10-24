package domain.login

object LoginHolder {
    internal lateinit var loginProvider: LoginProvider
    internal lateinit var addAccountProvider: AddAccountProvider

    fun loginProvider(it: LoginProvider) {
        loginProvider = it
    }

    fun addAccountProvider(it: AddAccountProvider) {
        addAccountProvider = it
    }
}
