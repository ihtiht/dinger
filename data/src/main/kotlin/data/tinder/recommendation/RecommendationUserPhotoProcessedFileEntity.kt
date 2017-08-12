package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Px

@Entity(indices = arrayOf(Index("url")))
internal class RecommendationUserPhotoProcessedFileEntity(
        @JvmField
        @Px
        var widthPx: Int,
        @PrimaryKey
        var url: String,
        @JvmField
        @Px
        var heightPx: Int)
