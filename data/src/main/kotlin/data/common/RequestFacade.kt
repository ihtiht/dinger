package data.common

import io.reactivex.Single

/**
 * Defines how a typical request looks.
 */
internal abstract class RequestFacade<in RequestModel, ResponseModel, MappedModel>(
        val source: RequestSource<RequestModel, ResponseModel>,
        val entityMapper: EntityMapper<ResponseModel, MappedModel>)
    : Fetchable<RequestModel, MappedModel> {
    override fun fetch(parameters: RequestModel) = map(source.fetch(parameters))

    /**
     * Maps items on the given series to another model.
     * @param sourceStream The series whose items are to be mapped.
     */
    fun map(sourceStream: Single<ResponseModel>): Single<MappedModel> =
            sourceStream.map { entityMapper.map(it) }
}
