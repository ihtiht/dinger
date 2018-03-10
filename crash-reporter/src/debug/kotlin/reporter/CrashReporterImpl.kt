package reporter

import android.util.Log
import com.google.firebase.crash.FirebaseCrash

internal sealed class CrashReporterImpl : CrashReporter {
    object Firebase : CrashReporterImpl() {
        override fun report(throwable: Throwable) = FirebaseCrash.report(throwable).also {
            Log.i("Firebase-debug-logs", throwable.message, throwable)
        }
    }

    object Crashlytics : CrashReporterImpl() {
        override fun report(throwable: Throwable) = FirebaseCrash.report(throwable).also {
            Log.i("Crashlytics-debug-logs", throwable.message, throwable)
        }
    }
}
