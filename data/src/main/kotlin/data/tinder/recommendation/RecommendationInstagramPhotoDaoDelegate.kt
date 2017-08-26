package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationInstagramPhotoDaoDelegate(
        private val instagramDao: RecommendationUserInstagramPhotoDao,
        private val userInstagramDao: RecommendationUserInstagram_InstagramPhotoDao)
    : CollectibleDaoDelegate<ResolvedRecommendationInstagramPhoto>() {
        override fun selectByPrimaryKey(primaryKey: String) =
                instagramDao.selectInstagramPhotoByLink(primaryKey).firstOrNull()?.let {
                    return@let ResolvedRecommendationInstagramPhoto(
                            link = it.link,
                            imageUrl = it.imageUrl,
                            thumbnailUrl = it.thumbnailUrl,
                            ts = it.ts)
                } ?: ResolvedRecommendationInstagramPhoto.NONE

    override fun insertResolved(source: ResolvedRecommendationInstagramPhoto) =
            instagramDao.insertInstagramPhoto(RecommendationUserInstagramPhotoEntity(
                    link = source.link,
                    imageUrl = source.imageUrl,
                    thumbnailUrl = source.thumbnailUrl,
                    ts = source.ts))

    fun insertResolvedForInstagramUsername(
            instagramUsername: String,
            instagramPhotos: Iterable<ResolvedRecommendationInstagramPhoto>) {
        instagramPhotos.forEach {
            insertResolved(it)
            userInstagramDao.insertInstagram_Photo(
                    RecommendationUserInstagramEntity_RecommendationUserInstagramPhotoEntity(
                            recommendationUserInstagramEntityUsername = instagramUsername,
                            recommendationUserInstagramPhotoEntityLink = it.link))
        }
    }
}
