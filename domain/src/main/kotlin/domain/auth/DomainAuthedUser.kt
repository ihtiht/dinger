package domain.auth

data class DomainAuthedUser(
        private val isNewUser: Boolean,
        val apiKey: String)
