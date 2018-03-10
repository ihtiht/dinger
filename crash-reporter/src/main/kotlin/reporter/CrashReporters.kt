package reporter

abstract class CrashReporters {
    companion object {
        fun firebase(): CrashReporter = CrashReporterImpl.Firebase
    }
}
