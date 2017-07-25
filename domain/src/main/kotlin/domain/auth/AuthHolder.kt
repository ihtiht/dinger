package domain.auth

object AuthHolder {
    internal lateinit var accountManager: AccountManager

    fun accountManager(accountManager: AccountManager){
        this.accountManager = accountManager
    }
}
