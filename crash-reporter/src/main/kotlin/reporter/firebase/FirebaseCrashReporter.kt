package reporter.firebase

import com.google.firebase.crash.FirebaseCrash
import reporter.CrashReporter

object FirebaseCrashReporter : CrashReporter {
    override fun report(throwable: Throwable) {
        FirebaseCrash.report(throwable)
    }
}
