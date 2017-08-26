package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserCommonConnectionEntityId")),
        primaryKeys = arrayOf("recommendationUserCommonConnectionEntityId",
                "recommendationUserCommonConnectionPhotoEntitySmall"))
internal class RecommendationUserCommonConnectionEntity_PhotoEntity(
    var recommendationUserCommonConnectionEntityId: String,
    var recommendationUserCommonConnectionPhotoEntitySmall: String)
