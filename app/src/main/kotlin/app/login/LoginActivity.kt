package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.stoyicker.dinger.R

internal class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
