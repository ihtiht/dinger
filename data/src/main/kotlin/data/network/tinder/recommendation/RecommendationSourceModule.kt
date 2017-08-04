package data.network.tinder.recommendation

import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.moshi.MoshiParserFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import data.network.tinder.TinderApi
import data.network.tinder.TinderApiModule
import okio.BufferedSource
import java.util.*
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
            StoreBuilder.parsedWithKey<Unit, BufferedSource, RecommendationResponse>()
                    .fetcher({ fetcher(api) })
                    .parser(MoshiParserFactory
                            .createSourceParser(
                                    Moshi.Builder()
                                            .add(Date::class.java, Rfc3339DateJsonAdapter())
                                            .build(),
                                    RecommendationResponse::class.java))
                    .networkBeforeStale()
                    .open()

    @Provides
    @Singleton
    fun source(store: DaggerLazy<Store<RecommendationResponse, Unit>>)
            = RecommendationSource(store)

    private fun fetcher(api: TinderApi) = api.getRecommendations().map { it.source() }
}
