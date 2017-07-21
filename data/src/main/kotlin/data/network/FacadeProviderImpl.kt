package data.network

import data.network.tinder.TinderApiRepositoryImpl
import domain.repository.FacadeProvider

internal object FacadeProviderImpl : FacadeProvider {
    override fun tinderApiRepository() = TinderApiRepositoryImpl
}
