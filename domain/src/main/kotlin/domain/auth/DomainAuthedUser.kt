package domain.auth

data class DomainAuthedUser(
        private val isNewUser: Boolean,
        private val apiKey: String)
