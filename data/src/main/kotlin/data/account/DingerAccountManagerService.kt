package data.account

import android.app.Service
import android.content.Intent
import android.os.IBinder
import data.ComponentHolder
import javax.inject.Inject

internal class DingerAccountManagerService : Service() {
    @Inject
    lateinit var accountManagerImpl: AppAccountManagerImpl

    override fun onBind(intent: Intent): IBinder {
        ComponentHolder.accountComponent.inject(this)
        return accountManagerImpl.iBinder
    }
}
