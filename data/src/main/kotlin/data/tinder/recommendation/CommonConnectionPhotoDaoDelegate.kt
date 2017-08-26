package data.tinder.recommendation

import data.DaoDelegate

internal class CommonConnectionPhotoDaoDelegate(
        private val photoDao: RecommendationUserCommonConnectionPhotoDao,
        private val commonConnectionPhotoDao: RecommendationUserCommonConnection_PhotoDao)
    : DaoDelegate<ResolvedRecommendationCommonConnectionPhoto>() {
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
                            small = it.small,
                            medium = it.medium,
                            large = it.large))
        }
    }
}

