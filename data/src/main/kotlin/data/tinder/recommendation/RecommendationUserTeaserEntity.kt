package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("id")))
internal class RecommendationUserTeaserEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var description: String,
        var type: String)
