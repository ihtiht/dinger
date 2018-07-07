package reporter

internal sealed class CrashReporterImpl : CrashReporter {
    object Void : CrashReporterImpl() {
        override fun report(throwable: Throwable) = Unit
    }
}
