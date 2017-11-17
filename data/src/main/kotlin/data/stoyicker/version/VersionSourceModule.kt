package data.stoyicker.version

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.FluentMemoryPolicyBuilder
import com.nytimes.android.external.store3.base.impl.FluentStoreBuilder
import com.nytimes.android.external.store3.base.impl.StalePolicy
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.middleware.moshi.MoshiParserFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import data.crash.FirebaseCrashReporterModule
import data.network.ParserModule
import data.stoyicker.StoyickerApi
import data.stoyicker.StoyickerApiModule
import okio.BufferedSource
import reporter.CrashReporter
import javax.inject.Singleton
import dagger.Lazy as DaggerLazy

@Module(includes = arrayOf(
        ParserModule::class, StoyickerApiModule::class, FirebaseCrashReporterModule::class))
internal class VersionSourceModule {
    @Provides
    @Singleton
    fun store(moshiBuilder: Moshi.Builder, api: StoyickerApi) =
            FluentStoreBuilder.parsedWithKey<Unit, BufferedSource, VersionResponse>(
                    Fetcher { fetch(api) }) {
                parsers = listOf(MoshiParserFactory.createSourceParser(
                    moshiBuilder.build(), VersionResponse::class.java))
                memoryPolicy = FluentMemoryPolicyBuilder.build { memorySize = 0 }
                stalePolicy = StalePolicy.REFRESH_ON_STALE
            }

    @Provides
    @Singleton
    fun source(store: DaggerLazy<Store<VersionResponse, Unit>>,
               crashReporter: CrashReporter) = VersionSource(store, crashReporter)

    private fun fetch(api: StoyickerApi) = api.versionCheck().map { it.source() }
}
