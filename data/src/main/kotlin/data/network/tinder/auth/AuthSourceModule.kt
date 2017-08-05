package data.network.tinder.auth

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.FluentStoreBuilder
import com.nytimes.android.external.store3.base.impl.StalePolicy
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.middleware.moshi.MoshiParserFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import data.network.ParserModule
import data.network.tinder.TinderApi
import data.network.tinder.TinderApiModule
import okio.BufferedSource
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

/**
 * Module used to provide stuff required by TopRequestSource objects.
 */
@Module(includes = arrayOf(ParserModule::class, TinderApiModule::class))
internal class AuthSourceModule {
    @Provides
    @Singleton
    fun store(moshiBuilder: Moshi.Builder, api: TinderApi) =
            FluentStoreBuilder.parsedWithKey<AuthRequestParameters, BufferedSource, AuthResponse>(
                    Fetcher { fetch(it, api) }) {
                parsers = listOf(MoshiParserFactory.createSourceParser(
                    moshiBuilder.build(),
                    AuthResponse::class.java))
                stalePolicy = StalePolicy.NETWORK_BEFORE_STALE
            }

    @Provides
    @Singleton
    fun source(store: DaggerLazy<Store<AuthResponse, AuthRequestParameters>>) = AuthSource(store)

    private fun fetch(requestParameters: AuthRequestParameters, api: TinderApi) =
            api.login(requestParameters).map { it.source() }
}
