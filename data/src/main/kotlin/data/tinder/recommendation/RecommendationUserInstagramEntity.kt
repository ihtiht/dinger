package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(indices = arrayOf(Index("username")))
internal class RecommendationUserInstagramEntity(
        var profilePictureUrl: String,
        @Embedded
        var lastFetchTime: Date,
        var mediaCount: Int,
        var completedInitialFetch: Boolean,
        @PrimaryKey
        var username: String)
