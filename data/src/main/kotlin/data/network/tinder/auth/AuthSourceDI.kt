package data.network.tinder.auth

import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.moshi.MoshiParserFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import data.network.tinder.TinderApi
import data.network.tinder.TinderApiModule
import okio.BufferedSource
import javax.inject.Singleton

@Component(modules = arrayOf(TinderApiModule::class, AuthSourceModule::class))
@Singleton
internal interface AuthSourceComponent

/**
 * Module used to provide stuff required by TopRequestSource objects.
 */
@Module(includes = arrayOf(TinderApiModule::class))
internal class AuthSourceModule {
    @Provides
    @Singleton
    fun store(api: TinderApi) =
            StoreBuilder.parsedWithKey<AuthRequest, BufferedSource, AuthResponse>()
                    .fetcher({ fetcher(it, api) })
                    .parser(MoshiParserFactory.createSourceParser<AuthResponse>(
                            AuthResponse::class.java))
                    .networkBeforeStale()
                    .open()

    private fun fetcher(request: AuthRequest, api: TinderApi) =
            api.login(request).map { it.source() }
}
