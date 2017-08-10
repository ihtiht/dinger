package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
internal class RecommendationUserSchoolEntity(
        var name: String,
        @PrimaryKey
        var id: String
)
