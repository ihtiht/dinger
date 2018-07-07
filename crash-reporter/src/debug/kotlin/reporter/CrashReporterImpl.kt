package reporter

import android.util.Log

internal sealed class CrashReporterImpl : CrashReporter {
    object Void : CrashReporterImpl() {
        override fun report(throwable: Throwable): Unit {
            Log.i("Void-debug-logs", throwable.message, throwable)
        }
    }
}
