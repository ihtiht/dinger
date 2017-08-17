package data.tinder.recommendation

import data.network.EntityMapper

internal class RecommendationRequestEntityMapper : EntityMapper<Unit, Unit> {
    override fun from(source: Unit) = source
}
