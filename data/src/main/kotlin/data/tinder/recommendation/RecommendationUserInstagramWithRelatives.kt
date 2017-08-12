package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserInstagramWithRelatives(
        @Embedded
        var recommendationUserInstagram: RecommendationUserInstagram,
        @Relation(parentColumn = "username", entityColumn = "link")
        var photos: Set<RecommendationUserInstagramPhotoEntity>)
