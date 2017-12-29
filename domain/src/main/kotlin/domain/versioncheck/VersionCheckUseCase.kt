package domain.versioncheck

import domain.interactor.SingleDisposableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class VersionCheckUseCase(
        private val appVersion: Int,
        asyncExecutionScheduler: Scheduler,
        postExecutionScheduler: Scheduler)
    : SingleDisposableUseCase<DomainVersionCheckDescription>(
        asyncExecutionScheduler, postExecutionScheduler) {
    override fun buildUseCase(): Single<DomainVersionCheckDescription> =
            VersionCheckHolder.versionCheck.versionCheck().map { it.run {
                DomainVersionCheckDescription(
                        dialogTitle = dialogTitle,
                        dialogBody = dialogBody,
                        positiveButtonText = positiveButtonText,
                        downloadUrl = downloadUrl,
                        isUpToDate = appVersion >= newVersion)
            }
            }
}
