package data.tinder.auth

import com.nytimes.android.external.store3.base.impl.Store
import data.network.RequestSource
import reporter.CrashReporter
import dagger.Lazy as DaggerLazy

internal class AuthSource(
        storeAccessor: DaggerLazy<Store<AuthResponse, AuthRequestParameters>>,
        crashReporter: CrashReporter)
    : RequestSource<AuthRequestParameters, AuthResponse>(storeAccessor.get(), crashReporter)
