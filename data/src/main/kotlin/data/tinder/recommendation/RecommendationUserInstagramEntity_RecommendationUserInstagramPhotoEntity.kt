package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("recommendationUserInstagramEntityId")))
internal class RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var recommendationUserInstagramEntityId: String,
    var recommendationUserInstagramPhotoEntityId: String)
