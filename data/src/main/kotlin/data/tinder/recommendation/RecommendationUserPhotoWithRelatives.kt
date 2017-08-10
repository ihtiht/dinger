package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserPhotoWithRelatives(
        @Embedded
        var recommendationUserPhotoEntity: RecommendationUserPhotoEntity,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserPhotoEntityId",
                entity = RecommendationUserPhotoEntity_RecommendationUserPhotoProcessedFile::class,
                projection = arrayOf("recommendationUserPhotoProcessedFileEntityId"))
        var processedFiles: Set<String>) {
    constructor() : this(RecommendationUserPhotoEntity(id = "", url = ""), emptySet())
}
