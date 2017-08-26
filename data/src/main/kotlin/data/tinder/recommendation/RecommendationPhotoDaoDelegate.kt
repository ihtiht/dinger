package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationPhotoDaoDelegate(
        private val photoDao: RecommendationUserPhotoDao,
        private val userPhotoDao: RecommendationUser_PhotoDao,
        private val processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate)
    : CollectibleDaoDelegate<ResolvedRecommendationPhoto>() {
    override fun insertResolved(source: ResolvedRecommendationPhoto) {
        processedFileDaoDelegate.insertResolvedForPhotoId(source.id, source.processedFiles)
        photoDao.insertPhoto(RecommendationUserPhotoEntity(id = source.id, url = source.url))
    }

    fun insertResolvedForUserId(userId: String, photos: Iterable<ResolvedRecommendationPhoto>) {
        photos.forEach {
            insertResolved(it)
            userPhotoDao.insertUser_Photo(RecommendationUserEntity_RecommendationUserPhotoEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserPhotoEntityId = it.id))
        }
    }
}
