package domain.repository

/**
 * Provides facades to the different APIs.
 */
interface FacadeProvider {
    fun tinderApiRepository(): TinderApiRepository
}
