package reporter

import com.google.firebase.crash.FirebaseCrash

sealed class CrashReporter {
    abstract fun report(throwable: Throwable)

    internal class Firebase : CrashReporter() {
        override fun report(throwable: Throwable) {
            FirebaseCrash.report(throwable)
        }
    }

    companion object {
        fun firebase(): CrashReporter = CrashReporter.Firebase()
    }
}
