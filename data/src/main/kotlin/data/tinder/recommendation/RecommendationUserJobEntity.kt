package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = arrayOf(Index("id")))
internal class RecommendationUserJobEntity(
        @PrimaryKey(autoGenerate = true)
        var id: String,
        @Embedded(prefix = "company_")
        var company: RecommendationUserJobCompany,
        @Embedded(prefix = "title_")
        var title: RecommendationUserJobTitle)
