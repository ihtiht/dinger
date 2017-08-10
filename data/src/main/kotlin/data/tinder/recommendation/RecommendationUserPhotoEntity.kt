package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("id")))
internal class RecommendationUserPhotoEntity(
        @PrimaryKey
        var id: String,
        var url: String)
