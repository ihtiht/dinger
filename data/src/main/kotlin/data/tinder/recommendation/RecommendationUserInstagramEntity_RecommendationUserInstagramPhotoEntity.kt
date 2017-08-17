package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("recommendationUserInstagramEntityUsername")),
        primaryKeys = arrayOf("recommendationUserInstagramEntityUsername",
                "recommendationUserInstagramPhotoEntityLink"))
internal class RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity(
    var recommendationUserInstagramEntityUsername: String,
    var recommendationUserInstagramPhotoEntityLink: String)
