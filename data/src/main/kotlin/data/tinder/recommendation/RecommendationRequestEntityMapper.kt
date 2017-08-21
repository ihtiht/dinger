package data.tinder.recommendation

import data.EntityMapper

internal class RecommendationRequestEntityMapper : EntityMapper<Unit, Unit> {
    override fun from(source: Unit) = source
}
