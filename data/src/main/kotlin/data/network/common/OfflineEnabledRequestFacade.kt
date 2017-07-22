package data.network.common

/**
 * Defines how a typical request looks.
 */
internal abstract class OfflineEnabledRequestFacade<
        in RequestModel, MappedRequestModel, ResponseModel, MappedModel>(
        source: RequestSource<MappedRequestModel, ResponseModel>,
        requestMapper: EntityMapper<RequestModel, MappedRequestModel>,
        responseMapper: EntityMapper<ResponseModel, MappedModel>)
    : Gettable<RequestModel, MappedModel>,
        RequestFacade<RequestModel, MappedRequestModel, ResponseModel, MappedModel>(
        source, requestMapper, responseMapper) {
    override fun get(parameters: RequestModel) = map(source.get(requestMapper.transform(parameters)))
}
