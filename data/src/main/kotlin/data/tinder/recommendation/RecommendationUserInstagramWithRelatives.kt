package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import java.util.Date

internal class RecommendationUserInstagramWithRelatives(
        @Embedded
        var recommendationUserInstagram: RecommendationUserInstagramEntity,
        @Relation(parentColumn = "username",
                entityColumn = "recommendationUserInstagramEntityUsername",
                entity
                = RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity::class,
                projection = arrayOf("recommendationUserInstagramPhotoEntityLink"))
        var photos: Set<String>) {
    constructor() : this(RecommendationUserInstagramEntity(
            profilePictureUrl = "",
            lastFetchTime = Date(),
            mediaCount = 0,
            completedInitialFetch = false,
            username = ""),
            emptySet())
}
