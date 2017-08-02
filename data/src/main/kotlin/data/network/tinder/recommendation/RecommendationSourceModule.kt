package data.network.tinder.recommendation

import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.moshi.MoshiParserFactory
import dagger.Module
import dagger.Provides
import data.network.tinder.TinderApi
import data.network.tinder.TinderApiModule
import data.network.tinder.auth.AuthRequestParameters
import data.network.tinder.auth.AuthResponse
import okio.BufferedSource
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

/**
 * Module used to provide stuff required by TopRequestSource objects.
 */
@Module(includes = arrayOf(TinderApiModule::class))
internal class RecommendationSourceModule {
    @Provides
    @Singleton
    fun store(api: TinderApi) =
            StoreBuilder.parsedWithKey<RecommendationRequestParameters, BufferedSource, RecommendationResponse>()
                    .fetcher({ fetcher(it, api) })
                    .parser(MoshiParserFactory
                            .createSourceParser(RecommendationResponse::class.java))
                    .networkBeforeStale()
                    .open()

    @Provides
    @Singleton
    fun source(store: DaggerLazy<Store<RecommendationResponse, RecommendationRequestParameters>>)
            = RecommendationSource(store)

    private fun fetcher(requestParameters: RecommendationRequestParameters, api: TinderApi) =
            api.getRecommendations(requestParameters).map { it.source() }
}
