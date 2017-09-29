package domain.auth

object LoginHolder {
    internal lateinit var loginRecommendationProvider: LoginProvider

    fun loginRecommendationProvider(it: LoginProvider) {
        loginRecommendationProvider = it
    }
}
