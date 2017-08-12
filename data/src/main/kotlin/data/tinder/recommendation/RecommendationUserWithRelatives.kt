package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserWithRelatives(
        @Embedded
        var recommendationUserEntity: RecommendationUserEntity,
//      @Relation(parentColumn = "id", entityColumn ="TODO RecommendationCommonConnectionEntity id when common connections are supported")
//      var commonConnections: Set<RecommendationCommonConnectionEntity>,
        @Relation(parentColumn = "id", entityColumn = "id")
        var commonInterests: Set<RecommendationInterestEntity>,
        @Relation(parentColumn = "id", entityColumn = "id")
        var photos: Set<RecommendationUserPhotoEntity>,
        @Relation(parentColumn = "id", entityColumn = "id")
        var jobs: Set<RecommendationUserJobEntity>,
        @Relation(parentColumn = "id", entityColumn = "id")
        var schools: Set<RecommendationUserSchoolEntity>,
        @Relation(parentColumn = "id", entityColumn = "id")
        var teasers: Set<RecommendationUserTeaserEntity>)
