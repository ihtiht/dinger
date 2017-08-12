package data.tinder.recommendation

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

@Entity(indices = arrayOf(Index("id")))
internal class RecommendationUserPhotoEntity(
        @PrimaryKey
        val id: String,
        val url: String,
        @Relation(parentColumn = "id", entityColumn = "url")
        val processedFiles: Set<RecommendationUserPhotoProcessedFile>)
