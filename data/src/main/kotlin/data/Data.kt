package data

import data.network.DataFacadeProvider
import domain.repository.DomainFacadeProvider

class Data {
    companion object {
        fun facadeProvider(): DomainFacadeProvider = DataFacadeProvider()
    }
}
