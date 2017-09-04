package data.tinder.recommendation

import android.os.Bundle
import tracker.TrackedDataMarshaller

internal class RecommendationResponseTrackedDataMarshaller(
        private val recommendationDelegate: TrackedDataMarshaller<Recommendation>)
    : TrackedDataMarshaller<RecommendationResponse> {
    override fun marshall(source: RecommendationResponse) = Bundle().apply {
        putString("message", source.message)
        putInt("status", source.status)
        if (source.recommendations != null) {
            putStringArray("results", source.recommendations.fold(emptyArray()) {
                acc, it ->
                val id = "result_${it.user.id}"
                putBundle(id, recommendationDelegate.marshall(it))
                arrayOf(id, *acc)
            })
        }
    }
}

internal class RecommendationMarshaller(
        private val userDelegate: TrackedDataMarshaller<RecommendationUser>)
    : TrackedDataMarshaller<Recommendation> {
    override fun marshall(source: Recommendation) = Bundle().apply {
        putBoolean("group_matched", source.groupMatched)
        putString("type", source.type)
        putBundle("user", userDelegate.marshall(source.user))
    }
}

internal class RecommendationUserMarshaller(
        private val commonConnectionDelegate: TrackedDataMarshaller<RecommendationUserCommonConnection>,
        private val commonInterestDelegate: TrackedDataMarshaller<RecommendationInterest>,
        private val photoDelegate: TrackedDataMarshaller<RecommendationUserPhoto>,
        private val instagramDelegate: TrackedDataMarshaller<RecommendationUserInstagram>,
        private val jobDelegate: TrackedDataMarshaller<RecommendationUserJob>,
        private val schoolDelegate: TrackedDataMarshaller<RecommendationUserSchool>,
        private val teaserDelegate: TrackedDataMarshaller<RecommendationUserTeaser>,
        private val spotifyThemeTrackDelegate: TrackedDataMarshaller<RecommendationUserSpotifyThemeTrack>)
    : TrackedDataMarshaller<RecommendationUser> {
    override fun marshall(source: RecommendationUser) = Bundle().apply {
        putInt("distance_mi", source.distanceMiles)
        putStringArray("common_connections", source.commonConnections.fold(emptyArray()) {
            acc, it ->
            val id = "common_connection_${it.id}"
            putBundle(id, commonConnectionDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putInt("connection_count", source.connectionCount)
        putStringArray("common_interests", source.commonInterests.fold(emptyArray()) { acc, it ->
            val id = "common_interest_${it.id}"
            putBundle(id, commonInterestDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putString("content_hash", source.contentHash)
        putString("_id", source.id)
        putLong("birth_date", source.birthDate.time)
        putString("name", source.name)
        putLong("ping_time", source.pingTime.time)
        putStringArray("photos", source.photos.fold(emptyArray()) { acc, it ->
            val id = "photo_${it.id}"
            putBundle(id, photoDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        source.instagram?.let { putBundle("instagram", instagramDelegate.marshall(it)) }
        putStringArray("jobs", source.jobs.fold(emptyArray()) { acc, it ->
            val id = "job_company_name=${it.company.name}_title_name=${it.title?.name}"
            putBundle(id, jobDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putStringArray("schools", source.schools.fold(emptyArray()) { acc, it ->
            val id = "school_${it.id}"
            putBundle(id, schoolDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putBundle("teaser", teaserDelegate.marshall(source.teaser))
        putStringArray("teasers", source.teasers.fold(emptyArray()) { acc, it ->
            val id = "teaser_type=${it.type}_description=${it.description}"
            putBundle(id, teaserDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putInt("s_number", source.sNumber)
        source.spotifyThemeTrack?.let {
            putBundle(
                    "spotify_theme_track",
                    spotifyThemeTrackDelegate.marshall(it))
        }
        putInt("gender", source.gender)
        putString("birth_date_info", source.birthDateInfo)
        putBoolean("group_matched", source.groupMatched)
    }
}

internal class CommonConnectionMarshaller(
        private val photoDelegate: TrackedDataMarshaller<RecommendationUserCommonConnectionPhoto>)
    : TrackedDataMarshaller<RecommendationUserCommonConnection> {
    override fun marshall(source: RecommendationUserCommonConnection) = Bundle().apply {
        putString("id", source.id)
        putString("name", source.name)
        putString("degree", source.degree)
        putStringArray("photos", source.photos.fold(emptyArray()) { acc, it ->
            val id = "photo_small=${it.small}_medium=${it.medium}_large=${it.large}"
            putBundle(id, photoDelegate.marshall(it))
            arrayOf(id, *acc)
        })
    }
}

internal class CommonConnectionPhotoMarshaller
    : TrackedDataMarshaller<RecommendationUserCommonConnectionPhoto> {
    override fun marshall(source: RecommendationUserCommonConnectionPhoto) = Bundle().apply {
        putString("small", source.small)
        putString("medium", source.medium)
        putString("large", source.large)
    }
}

internal class CommonInterestMarshaller : TrackedDataMarshaller<RecommendationInterest> {
    override fun marshall(source: RecommendationInterest) = Bundle().apply {
        putString("id", source.id)
        putString("name", source.name)
    }
}

internal class UserPhotoMarshaller(
        private val processedFileDelegate: TrackedDataMarshaller<RecommendationUserPhotoProcessedFile>)
    : TrackedDataMarshaller<RecommendationUserPhoto> {
    override fun marshall(source: RecommendationUserPhoto) = Bundle().apply {
        putString("id", source.id)
        putString("url", source.url)
        putStringArray("processed_files", source.processedFiles.fold(emptyArray()) { acc, it ->
            val id = "processed_file_${it.url}"
            putBundle(id, processedFileDelegate.marshall(it))
            arrayOf(id, *acc)
        })
    }
}

internal class ProcessedFileMarshaller
    : TrackedDataMarshaller<RecommendationUserPhotoProcessedFile> {
    override fun marshall(source: RecommendationUserPhotoProcessedFile) = Bundle().apply {
        putInt("width", source.widthPx)
        putString("url", source.url)
        putInt("height", source.heightPx)
    }
}

internal class InstagramMarshaller(
        private val photoDelegate: TrackedDataMarshaller<RecommendationUserInstagramPhoto>)
    : TrackedDataMarshaller<RecommendationUserInstagram> {
    override fun marshall(source: RecommendationUserInstagram) = Bundle().apply {
        putString("profile_picture_url", source.profilePictureUrl)
        putLong("last_fetch_time", source.lastFetchTime.time)
        putInt("media_count", source.mediaCount)
        putStringArray("photos", source.photos.fold(emptyArray()) { acc, it ->
            val id = "photo_${it.link}"
            putBundle(id, photoDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putBoolean("completed_initial_fetch", source.completedInitialFetch)
        putString("username", source.username)
    }
}

internal class InstagramPhotoMarshaller : TrackedDataMarshaller<RecommendationUserInstagramPhoto> {
    override fun marshall(source: RecommendationUserInstagramPhoto) = Bundle().apply {
        putString("image_url", source.imageUrl)
        putString("link", source.link)
        putString("thumbnail_url", source.thumbnailUrl)
        putString("ts", source.ts)
    }
}

internal class JobMarshaller : TrackedDataMarshaller<RecommendationUserJob> {
    override fun marshall(source: RecommendationUserJob) = Bundle().apply {
        putString("company_name", source.company.name)
        source.title?.let { putString("title_name", it.name) }
    }
}

internal class SchoolMarshaller : TrackedDataMarshaller<RecommendationUserSchool> {
    override fun marshall(source: RecommendationUserSchool) = Bundle().apply {
        putString("id", source.id)
        putString("name", source.name)
    }
}

internal class TeaserMarshaller : TrackedDataMarshaller<RecommendationUserTeaser> {
    override fun marshall(source: RecommendationUserTeaser) = Bundle().apply {
        putString("type", source.type)
        putString("description", source.description)
    }
}

internal class SpotifyThemeTrackMarshaller(
        private val albumDelegate: TrackedDataMarshaller<RecommendationUserSpotifyThemeTrackAlbum>,
        private val artistDelegate: TrackedDataMarshaller<RecommendationUserSpotifyThemeTrackArtist>)
    : TrackedDataMarshaller<RecommendationUserSpotifyThemeTrack> {
    override fun marshall(source: RecommendationUserSpotifyThemeTrack) = Bundle().apply {
        putBundle("album", albumDelegate.marshall(source.album))
        putStringArray("", source.artists.fold(emptyArray()) { acc, it ->
            val id = "artist_${it.id}"
            putBundle(id, artistDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putString("preview_url", source.previewUrl)
        putString("name", source.name)
        putString("id", source.id)
        putString("uri", source.uri)
    }
}

internal class SpotifyThemeTrackAlbumMarshaller(
        private val processedFileDelegate: TrackedDataMarshaller<RecommendationUserPhotoProcessedFile>)
    : TrackedDataMarshaller<RecommendationUserSpotifyThemeTrackAlbum> {
    override fun marshall(source: RecommendationUserSpotifyThemeTrackAlbum) = Bundle().apply {
        putStringArray("images", source.images.fold(emptyArray()) { acc, it ->
            val id = "image_${it.url}"
            putBundle(id, processedFileDelegate.marshall(it))
            arrayOf(id, *acc)
        })
        putString("name", source.name)
        putString("id", source.id)
    }
}

internal class SpotifyThemeTrackArtistMarshaller
    : TrackedDataMarshaller<RecommendationUserSpotifyThemeTrackArtist> {
    override fun marshall(source: RecommendationUserSpotifyThemeTrackArtist) = Bundle().apply {
        putString("name", source.name)
        putString("id", source.id)
    }
}
