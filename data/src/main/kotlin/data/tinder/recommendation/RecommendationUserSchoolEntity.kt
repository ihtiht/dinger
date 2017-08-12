package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
internal class RecommendationUserSchoolEntity private constructor(
        private var name: String,
        @PrimaryKey
        private var id: String
)
