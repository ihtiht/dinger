package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.login_button
import org.stoyicker.dinger.R


internal class LoginActivity : Activity() {
    private lateinit var facebookLoginFeature: FacebookLoginFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindFacebookLoginFeature()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookLoginFeature.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        unbindFacebookLoginFeature()
        super.onDestroy()
    }

    private fun bindFacebookLoginFeature() {
        facebookLoginFeature = FacebookLoginFeature(this, login_button)
    }

    private fun unbindFacebookLoginFeature() = facebookLoginFeature.release(login_button)

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
