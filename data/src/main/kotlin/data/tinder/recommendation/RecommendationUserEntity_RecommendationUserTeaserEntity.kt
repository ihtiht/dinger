package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserEntityId")),
        primaryKeys = arrayOf("recommendationUserEntityId", "recommendationUserTeaserEntityId"))
internal class RecommendationUserEntity_RecommendationUserTeaserEntity(
    var recommendationUserEntityId: String,
    var recommendationUserTeaserEntityId: String)
