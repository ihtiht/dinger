package data.tinder.recommendation

import android.arch.persistence.room.RoomDatabase
import data.CollectibleDaoDelegate

internal class RecommendationSpotifyArtistDaoDelegate(appDatabase: RoomDatabase)
    : CollectibleDaoDelegate<ResolvedRecommendationSpotifyArtist>(),
        RecommendationSpotifyArtistDao by RecommendationSpotifyArtistDao_Impl(appDatabase),
        RecommendationSpotifyThemeTrack_ArtistDao
        by RecommendationSpotifyThemeTrack_ArtistDao_Impl(appDatabase) {
    override fun selectByPrimaryKey(primaryKey: String) =
            selectArtistById(primaryKey).firstOrNull()?.let {
                return@let ResolvedRecommendationSpotifyArtist(id = it.id, name = it.name)
            } ?: ResolvedRecommendationSpotifyArtist.NONE

    override fun insertResolved(source: ResolvedRecommendationSpotifyArtist) = insertArtist(
            RecommendationUserSpotifyThemeTrackArtistEntity(id = source.id, name = source.name))

    fun insertResolvedForTrackId(
            trackId: String, artists: Iterable<ResolvedRecommendationSpotifyArtist>) {
        artists.forEach {
            insertResolved(it)
            insertSpotifyThemeTrack_Artist(
                    RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity(
                            recommendationUserSpotifyThemeTrackArtistEntityId = trackId,
                            recommendationUserSpotifyThemeTrackEntityId = it.id))
        }
    }
}
