package data.network.tinder.recommendation

import com.nytimes.android.external.store3.base.impl.Store
import data.network.common.RequestSource
import dagger.Lazy as DaggerLazy

internal class RecommendationSource(
        storeAccessor: DaggerLazy<Store<RecommendationResponse, Unit>>)
    : RequestSource<Unit, RecommendationResponse>(storeAccessor.get())
