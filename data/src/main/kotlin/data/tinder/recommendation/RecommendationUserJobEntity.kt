package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
internal class RecommendationUserJobEntity private constructor(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        @Embedded
        private var company: RecommendationUserJobCompany,
        @Embedded
        private var title: RecommendationUserJobTitle)
