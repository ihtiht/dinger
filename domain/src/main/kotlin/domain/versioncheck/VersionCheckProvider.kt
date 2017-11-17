package domain.versioncheck

import io.reactivex.Single

interface VersionCheckProvider {
    fun versionCheck(): Single<DomainVersionCheckResponse>
}
