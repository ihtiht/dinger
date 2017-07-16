package data

import domain.repository.DomainFacadeProvider

class Data {
    companion object {
        fun facadeProvider(): DomainFacadeProvider = DataFacadeProvider()
    }
}
