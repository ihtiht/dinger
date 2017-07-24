package data.network.common

import com.google.firebase.crash.FirebaseCrash
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Single
import java.net.UnknownHostException

internal abstract class RequestSource<in RequestModel, ResponseModel>(
        private val store: Store<ResponseModel, RequestModel>)
    : Gettable<RequestModel, ResponseModel> {
    override fun fetch(parameters: RequestModel): Single<ResponseModel> {
        return operate(store.fetch(parameters).onErrorResumeNext { error ->
            get(parameters)
                    .doOnSuccess { _ ->
                        if (error !is UnknownHostException) {
                            FirebaseCrash.report(error)
                        }
                    }.
                    onErrorResumeNext(Single.error(error))
        })
    }

    override fun get(parameters: RequestModel): Single<ResponseModel> {
        return operate(store.get(parameters))
    }

    /**
     * Do whatever operations you want on the data at this level.
     * @param sourceStream The stream whose items are to be mapped.
     */
    open fun operate(sourceStream: Single<ResponseModel>) = sourceStream
}
