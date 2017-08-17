package data.network

/**
 * Defines how a typical request looks.
 */
internal abstract class OfflineEnabledRequestFacade<
        RequestModel, MappedRequestModel, ResponseModel, MappedModel>(
        source: RequestSource<MappedRequestModel, ResponseModel>,
        requestMapper: EntityMapper<RequestModel, MappedRequestModel>,
        responseMapper: EntityMapper<ResponseModel, MappedModel>)
    : Gettable<RequestModel, MappedModel>,
        RequestFacade<RequestModel, MappedRequestModel, ResponseModel, MappedModel>(
        source, requestMapper, responseMapper) {
    override fun get(parameters: RequestModel) = map(source.get(requestMapper.from(parameters)))
}
