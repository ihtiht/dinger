package domain.login

object LoginHolder {
    internal lateinit var loginRecommendationProvider: LoginProvider

    fun loginRecommendationProvider(it: LoginProvider) {
        loginRecommendationProvider = it
    }
}
