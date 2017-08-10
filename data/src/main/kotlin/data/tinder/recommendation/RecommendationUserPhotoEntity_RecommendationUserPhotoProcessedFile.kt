package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("recommendationUserPhotoEntityId")))
internal class RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFile(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var recommendationUserPhotoEntityId: String,
    var recommendationUserPhotoProcessedFileEntityId: String)
