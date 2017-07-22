package data.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder

internal class DingerAccountManagerService : Service() {
    override fun onBind(intent: Intent): IBinder = DingerAccountManager(applicationContext).iBinder
}
