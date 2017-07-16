package data.tinder.auth

import com.nytimes.android.external.store3.base.impl.Store
import data.common.RequestSource
import javax.inject.Inject
import dagger.Lazy as DaggerLazy

internal class AuthSource @Inject constructor(
        storeAccessor: DaggerLazy<Store<AuthResponse, AuthRequest>>)
    : RequestSource<AuthRequest, AuthResponse>(storeAccessor.get())
