package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("id")))
internal class RecommendationInterestEntity private constructor(
        @PrimaryKey
        private var id: String,
        private var name: String)
