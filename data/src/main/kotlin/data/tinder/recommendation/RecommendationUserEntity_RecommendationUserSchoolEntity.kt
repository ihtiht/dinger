package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserEntityId")),
        primaryKeys = arrayOf("recommendationUserEntityId", "recommendationUserSchoolEntityName"))
internal class RecommendationUserEntity_RecommendationUserSchoolEntity(
    var recommendationUserEntityId: String,
    var recommendationUserSchoolEntityName: String)
