package domain.auth

interface AccountManager {
    fun addAccount(id: String, token: String): Boolean
    fun isThereALoggedInUser(): Boolean
}
