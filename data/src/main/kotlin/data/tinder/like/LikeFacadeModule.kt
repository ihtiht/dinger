package data.tinder.like

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(LikeSourceModule::class))
internal class LikeFacadeModule {
    @Provides
    @Singleton
    fun requestObjectMapper() = LikeRequestObjectMapper()

    @Provides
    @Singleton
    fun responseObjectMapper() = LikeResponseObjectMapper()

    @Provides
    @Singleton
    fun facade(
            source: LikeSource,
            requestObjectMapper: LikeRequestObjectMapper,
            responseObjectMapper: LikeResponseObjectMapper) =
            LikeFacade(source, requestObjectMapper, responseObjectMapper)
}
