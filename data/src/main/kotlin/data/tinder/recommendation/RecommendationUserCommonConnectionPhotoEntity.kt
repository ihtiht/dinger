package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(indices = arrayOf(Index("small", "medium", "large")),
        primaryKeys = arrayOf("small", "medium", "large"))
internal class RecommendationUserCommonConnectionPhotoEntity(
        var small: String,
        var medium: String,
        var large: String)
