package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class CommonConnectionPhotoDaoDelegate(
        private val photoDao: RecommendationUserCommonConnectionPhotoDao,
        private val commonConnectionPhotoDao: RecommendationUserCommonConnection_PhotoDao)
    : CollectibleDaoDelegate<String, ResolvedRecommendationCommonConnectionPhoto>() {
    override fun insertResolved(source: ResolvedRecommendationCommonConnectionPhoto) {
        photoDao.insertPhoto(RecommendationUserCommonConnectionPhotoEntity(
                small = source.small, medium = source.medium, large = source.large))
    }

    fun insertResolvedForCommonConnectionId(
            commonConnectionId: String,
            photos: Iterable<ResolvedRecommendationCommonConnectionPhoto>) {
        photos.forEach {
            insertResolved(it)
            commonConnectionPhotoDao.insertCommonConnection_Photo(
                    RecommendationUserCommonConnectionEntity_PhotoEntity(
                            recommendationUserCommonConnectionEntityId = commonConnectionId,
                            recommendationUserCommonConnectionPhotoEntitySmall = it.small))
        }
    }
}

