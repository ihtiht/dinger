package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserEntityId")),
        primaryKeys = arrayOf("recommendationUserEntityId",
                "recommendationUserCommonFriendEntityId"))
internal class RecommendationUserEntity_RecommendationUserCommonFriendEntity(
    var recommendationUserEntityId: String,
    var recommendationUserCommonFriendEntityId: String)
