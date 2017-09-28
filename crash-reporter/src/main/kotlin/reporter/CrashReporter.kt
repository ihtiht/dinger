package reporter

interface CrashReporter {
    fun report(throwable: Throwable)
}
