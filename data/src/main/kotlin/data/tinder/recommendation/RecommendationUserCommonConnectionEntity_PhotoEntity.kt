package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserCommonConnectionEntityId")),
        primaryKeys = arrayOf("recommendationUserCommonConnectionEntityId",
                "small",
                "medium",
                "large"))
internal class RecommendationUserCommonConnectionEntity_PhotoEntity(
    var recommendationUserCommonConnectionEntityId: String,
    var small: String,
    var medium: String,
    var large: String)
