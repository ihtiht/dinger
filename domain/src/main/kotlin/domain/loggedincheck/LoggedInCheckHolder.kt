package domain.loggedincheck

object LoggedInCheckHolder {
    internal lateinit var loggedInCheckProvider: LoggedInCheckProvider

    fun loggedInCheckProvider(it: LoggedInCheckProvider){
        loggedInCheckProvider = it
    }
}
