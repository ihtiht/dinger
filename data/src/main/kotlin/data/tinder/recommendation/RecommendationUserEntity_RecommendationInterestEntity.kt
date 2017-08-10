package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("recommendationUserEntityId")))
internal class RecommendationUserEntity_RecommendationInterestEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var recommendationUserEntityId: String,
    var recommendationInterestEntityId: String)
