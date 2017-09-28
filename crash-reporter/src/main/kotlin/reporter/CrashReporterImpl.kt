package reporter

import com.google.firebase.crash.FirebaseCrash

internal sealed class CrashReporterImpl : CrashReporter {
    /**
     * Firebase is a singleton, but other crash reporters which can be instantiated with different
     * ids would not be.
     */
    object Firebase : CrashReporterImpl() {
        override fun report(throwable: Throwable) {
            FirebaseCrash.report(throwable)
        }
    }
}
