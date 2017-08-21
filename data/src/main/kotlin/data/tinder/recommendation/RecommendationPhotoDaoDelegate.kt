package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationPhotoDaoDelegate(
        appDatabase: RoomDatabase,
        private val processedFileDaoDelegate: RecommendationProcessedFileDaoDelegate)
    : CollectibleDaoDelegate<ResolvedRecommendationPhoto>(),
        RecommendationUserPhotoDao by RecommendationUserPhotoDao_Impl(appDatabase),
        RecommendationUser_PhotoDao by RecommendationUser_PhotoDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectPhotoById(primaryKey).firstOrNull()?.let {
                val processedFiles =
                        processedFileDaoDelegate.collectByPrimaryKeys(it.processedFiles)
                it.recommendationUserPhotoEntity.let {
                    return ResolvedRecommendationPhoto(
                            id = it.id,
                            url = it.url,
                            processedFiles = processedFiles)
                }
            } ?: ResolvedRecommendationPhoto(id = "", url = "", processedFiles = emptySet())

    override fun insertResolved(source: ResolvedRecommendationPhoto) {
        processedFileDaoDelegate.insertResolvedForPhotoId(source.id, source.processedFiles)
        insertPhoto(RecommendationUserPhotoEntity(id = source.id, url = source.url))
    }

    fun insertResolvedForUserId(userId: String, photos: Iterable<ResolvedRecommendationPhoto>) {
        photos.forEach {
            insertResolved(it)
            insertUser_Photo(RecommendationUserEntity_RecommendationUserPhotoEntity(
                    recommendationUserEntityId = userId,
                    recommendationUserPhotoEntityId = it.id))
        }
    }
}
