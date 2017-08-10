package data.tinder.recommendation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

internal class RecommendationUserSpotifyThemeTrackAlbumWithRelatives(
        @Embedded
        var recommendationUserSpotifyThemeTrackAlbum: RecommendationUserSpotifyThemeTrackAlbumEntity,
        @Relation(parentColumn = "id",
                entityColumn = "recommendationUserSpotifyThemeTrackAlbumEntityId",
                entity = RecommendationUserSpotifyThemeTrackAlbum_RecommendationUserPhotoProcessedFileEntity::class,
                projection = arrayOf("recommendationUserPhotoProcessedFileEntity"))
        var images: Set<String>) {
    constructor() : this(RecommendationUserSpotifyThemeTrackAlbumEntity(name = "", id = ""),
            emptySet())
}
