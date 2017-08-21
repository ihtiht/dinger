package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationInstagramPhotoDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationInstagramPhoto>(),
        RecommendationUserInstagramPhotoDao
        by RecommendationUserInstagramPhotoDao_Impl(appDatabase),
        RecommendationUserInstagram_InstagramPhotoDao
        by RecommendationUserInstagram_InstagramPhotoDao_Impl(appDatabase) {
        override fun selectByPrimaryKey(primaryKey: String) =
                selectInstagramPhotoByLink(primaryKey).firstOrNull()?.let {
                    return@let ResolvedRecommendationInstagramPhoto(
                            link = it.link,
                            imageUrl = it.imageUrl,
                            thumbnailUrl = it.thumbnailUrl,
                            ts = it.ts)
                } ?: ResolvedRecommendationInstagramPhoto(
                        link = "",
                        imageUrl = "",
                        thumbnailUrl = "",
                        ts = "")

    override fun insertResolved(source: ResolvedRecommendationInstagramPhoto) =
            insertInstagramPhoto(RecommendationUserInstagramPhotoEntity(
                    link = source.link,
                    imageUrl = source.imageUrl,
                    thumbnailUrl = source.thumbnailUrl,
                    ts = source.ts))

    fun insertResolvedForInstagramUsername(
            instagramUsername: String,
            instagramPhotos: Iterable<ResolvedRecommendationInstagramPhoto>) {
        instagramPhotos.forEach {
            insertResolved(it)
            insertInstagram_Photo(
                    RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity(
                            recommendationUserInstagramEntityUsername = instagramUsername,
                            recommendationUserInstagramPhotoEntityLink = it.link))
        }
    }
}
