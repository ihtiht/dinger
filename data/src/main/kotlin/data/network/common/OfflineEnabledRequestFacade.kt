package data.network.common

/**
 * Defines how a typical request looks.
 */
internal abstract class OfflineEnabledRequestFacade<in RequestModel, ResponseModel, MappedModel>(
        source: RequestSource<RequestModel, ResponseModel>,
        entityMapper: EntityMapper<ResponseModel, MappedModel>)
    : Gettable<RequestModel, MappedModel>, RequestFacade<RequestModel, ResponseModel, MappedModel>(
        source, entityMapper) {
    override fun get(parameters: RequestModel) = map(source.get(parameters))
}
