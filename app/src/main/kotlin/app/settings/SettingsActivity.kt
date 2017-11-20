package app.settings

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

internal class SettingsActivity : AppCompatActivity() {
    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }
}
