package data.autoswipe

import android.content.Context
import android.os.Build
import android.support.annotation.IntDef
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_ERROR
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_MORE_AVAILABLE
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_RATE_LIMITED
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_UNEXPECTED
import data.notification.NotificationManager
import domain.like.DomainLikedRecommendationAnswer
import org.stoyicker.dinger.data.R
import reporter.CrashReporter

internal class AutoSwipeReportHandler(
        private val notificationManager: NotificationManager,
        private val crashReporter: CrashReporter) {
    private var consumed = false
    private var likeCounter = 0
    private var matchCounter = 0

    fun addLikeAnswer(answer: DomainLikedRecommendationAnswer) {
        // The last like done comes with rateLimited too
        if (answer.rateLimitedUntilMillis == null || likeCounter == 0) {
            addLike()
            if (answer.matched) {
                addMatch()
            }
        }
    }

    private fun addMatch() { ++matchCounter }

    private fun addLike() { ++likeCounter }

    fun show(context: Context, @AutoSwipeResult result: Long) {
        // Show nothing on unsupported APIs to avoid spammy notifications
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return
        if (consumed) return
        consumed = true
        notificationManager.pop(
                channelName = R.string.autoswipe_notification_channel_name,
                title = context.getString(R.string.autoswipe_notification_group_title),
                body = context.getString(R.string.autoswipe_notification_group_body),
                category = NotificationManager.CATEGORY_SERVICE,
                groupName = context.getString(R.string.autoswipe_notification_group_name),
                isGroupSummary = true,
                priority = NotificationManager.PRIORITY_LOW)
        notificationManager.pop(
                channelName = R.string.autoswipe_notification_channel_name,
                title = generateTitle(context, likeCounter, matchCounter),
                body = generateBody(context, crashReporter, result),
                category = NotificationManager.CATEGORY_SERVICE,
                groupName = context.getString(R.string.autoswipe_notification_group_name),
                isGroupSummary = false,
                priority = NotificationManager.PRIORITY_LOW)
    }

    companion object {
        const val RESULT_UNEXPECTED = 0L
        const val RESULT_RATE_LIMITED = 1L
        const val RESULT_MORE_AVAILABLE = 2L
        const val RESULT_ERROR = 3L
    }
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(RESULT_UNEXPECTED, RESULT_RATE_LIMITED, RESULT_MORE_AVAILABLE, RESULT_ERROR)
internal annotation class AutoSwipeResult

private fun generateTitle(context: Context, likes: Int, matches: Int) = StringBuilder().apply {
    append(context.resources.getQuantityString(
            R.plurals.autoswipe_notification_title_swept, likes, likes))
    append(context.resources.getQuantityString(
            R.plurals.autoswipe_notification_title_matches, matches, matches))
}.toString()

private fun generateBody(
        context: Context,
        crashReporter: CrashReporter,
        @AutoSwipeResult result: Long) = when (result) {
    RESULT_RATE_LIMITED -> context.getString(R.string.autoswipe_notification_body_capped)
    RESULT_MORE_AVAILABLE -> context.getString(R.string.autoswipe_notification_body_more_available)
    RESULT_ERROR -> context.getString(R.string.autoswipe_notification_body_error)
    RESULT_UNEXPECTED -> {
        crashReporter.report(IllegalStateException(
                "Got RESULT_UNEXPECTED in the autoswipe report."))
        context.getString(R.string.autoswipe_notification_body_unexpected)
    }
    else -> throw IllegalStateException("Unexpected result $result in the autoswipe report.")
}
