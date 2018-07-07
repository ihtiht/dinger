package reporter

abstract class CrashReporters {
    companion object {
        fun void(): CrashReporter = CrashReporterImpl.Void
    }
}
