package data.tinder.recommendation

import data.network.EntityMapper

internal class RecommendationRequestEntityMapper : EntityMapper<Unit, Unit> {
    override fun transform(source: Unit) = source
}
