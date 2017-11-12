package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserWithRelatives(
        @Embedded
        var recommendationUserEntity: RecommendationUserEntity,
        @Relation(parentColumn = "id", entityColumn ="recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationUserCommonFriendEntity::class,
                projection = arrayOf("recommendationUserCommonFriendEntityId"))
        var commonFriends: Set<String>,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationLikeEntity::class,
                projection = arrayOf("recommendationLikeEntityId"))
        var commonLikes: Set<String>,
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
                projection = arrayOf("recommendationUserSchoolEntityName"))
        var schools: Set<String>,
        @Relation(parentColumn = "id", entityColumn = "recommendationUserEntityId",
                entity = RecommendationUserEntity_RecommendationUserTeaserEntity::class,
                projection = arrayOf("recommendationUserTeaserEntityId"))
        var teasers: Set<String>) {
    constructor() : this(
            RecommendationUserEntity.NONE,
            commonFriends = emptySet(),
            commonLikes = emptySet(),
            photos = emptySet(),
            jobs = emptySet(),
            schools = emptySet(),
            teasers = emptySet())
}
