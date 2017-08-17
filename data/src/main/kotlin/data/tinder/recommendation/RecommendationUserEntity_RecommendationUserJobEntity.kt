package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserEntityId")),
        primaryKeys = arrayOf("recommendationUserEntityId", "recommendationUserJobEntityId"))
internal class RecommendationUserEntity_RecommendationUserJobEntity(
    var recommendationUserEntityId: String,
    var recommendationUserJobEntityId: String)
