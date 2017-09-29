package data.account

import android.app.Service
import android.content.Intent
import android.os.IBinder
import javax.inject.Inject

internal class DingerAccountManagerService : Service() {
    @Inject
    lateinit var accountManagerImpl: AppLoggedInCheckProviderImpl

    override fun onBind(intent: Intent): IBinder {
        AccountComponentHolder.accountComponent.inject(this)
        return accountManagerImpl.iBinder
    }
}
