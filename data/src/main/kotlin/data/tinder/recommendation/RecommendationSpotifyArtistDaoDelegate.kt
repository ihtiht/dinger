package data.tinder.recommendation

import data.CollectibleDaoDelegate

internal class RecommendationSpotifyArtistDaoDelegate(
        private val artistDao: RecommendationSpotifyArtistDao,
        private val trackArtistDao: RecommendationSpotifyThemeTrack_ArtistDao)
    : CollectibleDaoDelegate<String, ResolvedRecommendationSpotifyArtist>() {
    override fun selectByPrimaryKey(primaryKey: String) =
            artistDao.selectArtistById(primaryKey).firstOrNull()?.let {
                return ResolvedRecommendationSpotifyArtist(
                        id = it.id,
                        name = it.name)
            } ?: ResolvedRecommendationSpotifyArtist.NONE

    override fun insertResolved(source: ResolvedRecommendationSpotifyArtist) =
            artistDao.insertArtist(RecommendationUserSpotifyThemeTrackArtistEntity(
                    id = source.id,
                    name = source.name))

    fun insertResolvedForTrackId(
            trackId: String, artists: Iterable<ResolvedRecommendationSpotifyArtist>) {
        artists.forEach {
            insertResolved(it)
            trackArtistDao.insertSpotifyThemeTrack_Artist(
                    RecommendationUserSpotifyThemeTrackEntity_RecommendationUserSpotifyThemeTrackArtistEntity(
                            recommendationUserSpotifyThemeTrackArtistEntityId = trackId,
                            recommendationUserSpotifyThemeTrackEntityId = it.id))
        }
    }
}
