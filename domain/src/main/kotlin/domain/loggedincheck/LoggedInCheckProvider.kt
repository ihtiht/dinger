package domain.loggedincheck

interface LoggedInCheckProvider {
    fun addAccount(id: String, token: String): Boolean
    fun isThereALoggedInUser(): Boolean
}
