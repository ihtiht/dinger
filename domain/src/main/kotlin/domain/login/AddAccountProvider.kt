package domain.login

interface AddAccountProvider {
    fun addAccount(id: String, token: String): Boolean
}
