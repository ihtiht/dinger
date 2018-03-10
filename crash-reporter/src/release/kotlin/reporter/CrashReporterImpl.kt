package reporter

import com.google.firebase.crash.FirebaseCrash

internal sealed class CrashReporterImpl : CrashReporter {
    object Firebase : CrashReporterImpl() {
        override fun report(throwable: Throwable) = FirebaseCrash.report(throwable)
    }

    object Crashlytics : CrashReporterImpl() {
        override fun report(throwable: Throwable) =
                com.crashlytics.android.Crashlytics.logException(throwable)
    }
}
