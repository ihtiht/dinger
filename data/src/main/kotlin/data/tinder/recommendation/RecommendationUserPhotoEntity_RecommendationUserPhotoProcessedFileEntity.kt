package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserPhotoEntityId")),
        primaryKeys = arrayOf("recommendationUserPhotoEntityId", "recommendationUserPhotoProcessedFileEntityUrl"))
internal class RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFileEntity(
    var recommendationUserPhotoEntityId: String,
    var recommendationUserPhotoProcessedFileEntityUrl: String)
