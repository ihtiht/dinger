package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_login.*
import org.stoyicker.dinger.R


internal class LoginActivity : Activity() {
    private lateinit var facebookLoginFeature: FacebookLoginFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindFacebookLoginFeature(login_button)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookLoginFeature.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        facebookLoginFeature.release(login_button)
        super.onDestroy()
    }

    private fun bindFacebookLoginFeature(loginButton: LoginButton) {
        facebookLoginFeature = FacebookLoginFeature(this, loginButton)
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
