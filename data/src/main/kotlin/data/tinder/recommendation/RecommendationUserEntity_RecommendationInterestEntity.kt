package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserEntityId")),
        primaryKeys = arrayOf("recommendationUserEntityId", "recommendationInterestEntityId"))
internal class RecommendationUserEntity_RecommendationInterestEntity(
    var recommendationUserEntityId: String,
    var recommendationInterestEntityId: String)
