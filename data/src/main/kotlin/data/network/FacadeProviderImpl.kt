package data.network

import data.tinder.TinderApiRepositoryImpl
import domain.repository.FacadeProvider

internal class FacadeProviderImpl : FacadeProvider {
    override fun tinderApiRepository() = TinderApiRepositoryImpl()
}
