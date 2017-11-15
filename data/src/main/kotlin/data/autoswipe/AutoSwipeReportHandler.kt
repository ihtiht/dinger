package data.autoswipe

import android.content.Context
import android.support.annotation.IntDef
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_ERROR
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_MORE_AVAILABLE
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_RATE_LIMITED
import data.autoswipe.AutoSwipeReportHandler.Companion.RESULT_UNEXPECTED
import data.notification.NotificationManager
import org.stoyicker.dinger.data.R
import reporter.CrashReporter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class AutoSwipeReportHandler(
        private val notificationManager: NotificationManager,
        private val crashReporter: CrashReporter) {
    private var likeCounter = 0
    private var matchCounter = 0

    fun addMatch() {
        addLike()
        ++matchCounter
    }

    fun addLike() { ++likeCounter }

    fun show(context: Context, @AutoSwipeResult result: Long, timestamp: Long) {
        notificationManager.pop(
                channelName = R.string.autoswipe_notification_channel_name,
                title = generateTitle(context, likeCounter, matchCounter),
                body = generateBody(context, crashReporter, result, timestamp),
                category = NotificationManager.CATEGORY_SERVICE,
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
    append(context.getString(R.string.autoswipe_notification_title_swept, likes))
    append(context.resources.getQuantityString(
            R.plurals.autoswipe_notification_title_matches, matches))
}.toString()

private fun generateBody(
        context: Context,
        crashReporter: CrashReporter,
        @AutoSwipeResult result: Long,
        timestamp: Long? = null) =
        timestamp.run { if (this == null) {
            ""
        } else {
            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(Date(this))
        } }.run {
            when (result) {
                RESULT_RATE_LIMITED -> context.getString(
                        R.string.autoswipe_notification_body_capped, this)
                RESULT_MORE_AVAILABLE -> context.getString(
                        R.string.autoswipe_notification_body_more_available, this)
                RESULT_ERROR -> context.getString(
                        R.string.autoswipe_notification_body_error, this)
                RESULT_UNEXPECTED -> {
                    crashReporter.report(IllegalStateException(
                            "Got RESULT_UNEXPECTED in the autoswipe report."))
                    context.getString(R.string.autoswipe_notification_body_unexpected)
                }
            else -> throw IllegalStateException("Unexpected result $result in the autoswipe report.")
        }
}
