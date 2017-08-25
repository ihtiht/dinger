package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.DaoDelegate

internal class RecommendationInstagramDaoDelegate(
        appDatabase: RoomDatabase,
        private val instagramPhotoDaoDelegate: RecommendationInstagramPhotoDaoDelegate)
    : DaoDelegate<ResolvedRecommendationInstagram>,
        RecommendationUserInstagramDao by RecommendationUserInstagramDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectInstagramByUsername(primaryKey).firstOrNull()?.let {
                val photos = instagramPhotoDaoDelegate.collectByPrimaryKeys(it.photos)
                it.recommendationUserInstagram.let {
                    return ResolvedRecommendationInstagram(
                            profilePictureUrl = it.profilePictureUrl,
                            lastFetchTime = it.lastFetchTime,
                            mediaCount = it.mediaCount,
                            completedInitialFetch = it.completedInitialFetch,
                            username = it.username,
                            photos = photos)
                }
            } ?: ResolvedRecommendationInstagram.NONE

    override fun insertResolved(source: ResolvedRecommendationInstagram) {
        instagramPhotoDaoDelegate.insertResolvedForInstagramUsername(source.username, source.photos)
        insertInstagram(
                RecommendationUserInstagramEntity(
                        profilePictureUrl = source.profilePictureUrl,
                        lastFetchTime = source.lastFetchTime,
                        mediaCount = source.mediaCount,
                        completedInitialFetch = source.completedInitialFetch,
                        username = source.username))
    }
}
