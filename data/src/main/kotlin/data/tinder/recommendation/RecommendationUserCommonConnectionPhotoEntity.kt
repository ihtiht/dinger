package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("small")))
internal class RecommendationUserCommonConnectionPhotoEntity(
        @PrimaryKey
        var small: String,
        var medium: String,
        var large: String)
