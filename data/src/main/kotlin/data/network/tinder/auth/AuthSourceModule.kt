package data.network.tinder.auth

import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
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
            StoreBuilder.parsedWithKey<AuthRequestParameters, BufferedSource, AuthResponse>()
                    .fetcher({ fetcher(it, api) })
                    .parser(MoshiParserFactory.createSourceParser(
                            moshiBuilder.build(),
                            AuthResponse::class.java))
                    .networkBeforeStale()
                    .open()

    @Provides
    @Singleton
    fun source(store: DaggerLazy<Store<AuthResponse, AuthRequestParameters>>) = AuthSource(store)

    private fun fetcher(requestParameters: AuthRequestParameters, api: TinderApi) =
            api.login(requestParameters).map { it.source() }
}
