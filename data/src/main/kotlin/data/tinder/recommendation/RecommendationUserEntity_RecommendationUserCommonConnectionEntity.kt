package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserEntityId")),
        primaryKeys = arrayOf("recommendationUserEntityId",
                "recommendationUserCommonConnectionEntityId"))
internal class RecommendationUserEntity_RecommendationUserCommonConnectionEntity(
    var recommendationUserEntityId: String,
    var recommendationUserCommonConnectionEntityId: String)
