package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import java.util.Date

internal class RecommendationUserWithRelatives(
        @Embedded
        var recommendationUserEntity: RecommendationUserEntity,
//      @Relation(parentColumn = "id", entityColumn ="TODO RecommendationCommonConnectionEntity id when common connections are supported")
//      var commonConnections: Set<RecommendationCommonConnectionEntity>,)
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationInterestEntity::class,
                projection = arrayOf("recommendationInterestEntityId"))
        var commonInterests: Set<String>,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationUserPhotoEntity::class,
                projection = arrayOf("recommendationUserPhotoEntityId"))
        var photos: Set<String>,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationUserJobEntity::class,
                projection = arrayOf("recommendationUserJobEntityId"))
        var jobs: Set<String>,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationUserSchoolEntity::class,
                projection = arrayOf("recommendationUserSchoolEntityId"))
        var schools: Set<String>,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationUserTeaserEntity::class,
                projection = arrayOf("recommendationUserTeaserEntityId"))
        var teasers: Set<String>) {
    constructor() : this(RecommendationUserEntity(distanceMiles = 0,
            connectionCount = 0,
            contentHash = "",
            id = "",
            birthDate = Date(),
            name = "",
            pingTime = Date(),
            instagram = "",
            teaser = "",
            sNumber = 0,
            spotifyThemeTrack = "",
            gender = 0,
            birthDateInfo = "",
            groupMatched = false,
            liked = false),
            commonInterests = emptySet(),
            photos = emptySet(),
            jobs = emptySet(),
            schools = emptySet(),
            teasers = emptySet())
}
