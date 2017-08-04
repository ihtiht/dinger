package data.network.tinder.recommendation

import data.network.common.EntityMapper

internal class RecommendationRequestEntityMapper : EntityMapper<Unit, Unit> {
    override fun transform(source: Unit) = source
}
