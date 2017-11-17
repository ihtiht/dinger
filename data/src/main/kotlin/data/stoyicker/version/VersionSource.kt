package data.stoyicker.version

import com.nytimes.android.external.store3.base.impl.Store
import data.network.RequestSource
import reporter.CrashReporter
import dagger.Lazy as DaggerLazy

internal class VersionSource(
        storeAccessor: DaggerLazy<Store<VersionResponse, Unit>>,
        crashReporter: CrashReporter)
    : RequestSource<Unit, VersionResponse>(storeAccessor.get(), crashReporter)
