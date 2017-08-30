package data.tinder.recommendation

import android.arch.lifecycle.Transformations
import domain.recommendation.DomainRecommendationUser

internal class RecommendationUserResolver(
        private val userDao: RecommendationUserDao,
        private val commonConnectionDaoDelegate: RecommendationCommonConnectionDaoDelegate,
        private val instagramDaoDelegate: RecommendationInstagramDaoDelegate,
        private val interestDaoDelegate: RecommendationInterestDaoDelegate,
        private val photoDaoDelegate: RecommendationPhotoDaoDelegate,
        private val jobDaoDelegate: RecommendationJobDaoDelegate,
        private val schoolDaoDelegate: RecommendationSchoolDaoDelegate,
        private val teaserDaoDelegate: RecommendationTeaserDaoDelegate,
        private val spotifyThemeTrackDaoDelegate: RecommendationSpotifyThemeTrackDaoDelegate) {
    fun insert(user: DomainRecommendationUser) = user.apply {
        userDao.insertUser(RecommendationUserEntity(
                distanceMiles = distanceMiles,
                connectionCount = connectionCount,
                contentHash = contentHash,
                id = id,
                birthDate = birthDate,
                name = name,
                instagram = instagram?.username,
                pingTime = pingTime,
                teaser = teaser.id,
                sNumber = sNumber,
                spotifyThemeTrack = spotifyThemeTrack?.id,
                gender = gender,
                birthDateInfo = birthDateInfo,
                groupMatched = groupMatched,
                liked = liked
        ))
        instagramDaoDelegate.insertDomain(instagram)
        teaserDaoDelegate.insertDomain(teaser)
        spotifyThemeTrackDaoDelegate.insertDomain(spotifyThemeTrack)
        commonConnectionDaoDelegate.insertDomainForUserId(id, commonConnections)
        interestDaoDelegate.insertDomainForUserId(id, commonInterests)
        photoDaoDelegate.insertDomainForUserId(id, photos)
        jobDaoDelegate.insertDomainForUserId(id, jobs)
        schoolDaoDelegate.insertDomainForUserId(id, schools)
        teaserDaoDelegate.insertDomainForUserId(id, teasers)
    }

    fun selectById(id: String) =
            Transformations.map(userDao.selectUserById(id)) { it.map { from(it) } }

    fun selectByFilterOnName(filter: String) =
            Transformations.map(userDao.selectUsersByFilterOnName(filter)) { it.map { from(it) } }

    private fun from(source: RecommendationUserWithRelatives): DomainRecommendationUser {
        val commonConnections =
                commonConnectionDaoDelegate.collectByPrimaryKeys(source.commonConnections)
        val commonInterests = interestDaoDelegate.collectByPrimaryKeys(source.commonInterests)
        val photos = photoDaoDelegate.collectByPrimaryKeys(source.photos)
        val jobs = jobDaoDelegate.collectByPrimaryKeys(source.jobs)
        val schools = schoolDaoDelegate.collectByPrimaryKeys(source.schools)
        val teasers = teaserDaoDelegate.collectByPrimaryKeys(source.teasers)
        source.recommendationUserEntity.let {
            return DomainRecommendationUser(
                    distanceMiles = it.distanceMiles,
                    commonConnections = commonConnections,
                    connectionCount = it.connectionCount,
                    contentHash = it.contentHash,
                    id = it.id,
                    birthDate = it.birthDate,
                    name = it.name,
                    pingTime = it.pingTime,
                    instagram = instagramDaoDelegate.selectByPrimaryKey(it.instagram),
                    teaser = teaserDaoDelegate.selectByPrimaryKey(it.teaser),
                    sNumber = it.sNumber,
                    spotifyThemeTrack =
                    spotifyThemeTrackDaoDelegate.selectByPrimaryKey(it.spotifyThemeTrack),
                    gender = it.gender,
                    birthDateInfo = it.birthDateInfo,
                    groupMatched = it.groupMatched,
                    liked = it.liked,
                    commonInterests = commonInterests,
                    photos = photos,
                    jobs = jobs,
                    schools = schools,
                    teasers = teasers)
        }
    }
}
