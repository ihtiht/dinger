package domain.versioncheck

object VersionCheckHolder {
    internal lateinit var versionCheckProvider: VersionCheckProvider

    fun versionCheckProvider(it: VersionCheckProvider) {
        versionCheckProvider = it
    }
}
