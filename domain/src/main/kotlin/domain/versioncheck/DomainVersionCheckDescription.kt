package domain.versioncheck

data class DomainVersionCheckDescription(
        val dialogTitle: CharSequence,
        val dialogBody: CharSequence,
        val positiveButtonText: CharSequence,
        val downloadUrl: String,
        val isUpToDate: Boolean)
