package domain.versioncheck

data class DomainVersionCheckResponse(
        val dialogTitle: CharSequence,
        val dialogBody: CharSequence,
        val positiveButtonText: CharSequence,
        val downloadUrl: String,
        val newVersion: Int)
