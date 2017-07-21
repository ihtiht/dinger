package domain.auth

data class DomainAuthRequestParameters(
        private val facebookId: String,
        private val facebookToken: String)
