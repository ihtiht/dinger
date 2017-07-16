package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun bindFacebookLoginFeature() {
        facebookLoginFeature = FacebookLoginFeature(this, login_button)
    }

    private fun unbindFacebookLoginFeature() = facebookLoginFeature.release(login_button)

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
