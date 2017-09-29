package domain.loggedincheck

interface LoggedInCheckProvider {
    fun isThereALoggedInUser(): Boolean
}
