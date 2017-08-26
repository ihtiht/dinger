package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationPhotoDaoDelegate(
        private val photoDao: RecommendationUserPhotoDao,
        private val userPhotoDao: RecommendationUser_PhotoDao,
        private val processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate)
    : CollectibleDaoDelegate<String, ResolvedRecommendationPhoto>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            photoDao.selectPhotoById(primaryKey).firstOrNull()?.let {
                val processedFiles =
                        processedFileDaoDelegate.collectByPrimaryKeys(it.processedFiles)
                it.recommendationUserPhotoEntity.let {
                    return ResolvedRecommendationPhoto(
                            id = it.id,
                            url = it.url,
                            processedFiles = processedFiles)
                }
            } ?: ResolvedRecommendationPhoto.NONE

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
