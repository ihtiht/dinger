package data.tinder.recommendation

import android.arch.persistence.room.*
import java.util.*

@Entity(indices = arrayOf(Index("id"),
        Index("name"),
        Index("instagram"),
        Index("teaser"),
        Index("spotifyThemeTrack")),
        foreignKeys = arrayOf(
                ForeignKey(entity = RecommendationUserInstagramEntity::class,
                        parentColumns = arrayOf("username"),
                        childColumns = arrayOf("instagram")),
                ForeignKey(entity = RecommendationUserTeaserEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("teaser")),
                ForeignKey(entity = RecommendationUserSpotifyThemeTrackEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("spotifyThemeTrack"))))
internal open class RecommendationUserEntity(
        var distanceMiles: Int,
        var connectionCount: Int,
        var contentHash: String,
        @PrimaryKey
        var id: String,
        @Embedded
        var birthDate: Date,
        var name: String,
        @Embedded
        var pingTime: Date,
        var instagram: String,
        var teaser: String,
        var sNumber: Int,
        var spotifyThemeTrack: String,
        var gender: Int,
        var birthDateInfo: String,
        var groupMatched: Boolean,
        var liked: Boolean)
