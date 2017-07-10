package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.facebook.AccessToken.getCurrentAccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.crash.FirebaseCrash
import kotlinx.android.synthetic.main.activity_login.*
import org.stoyicker.dinger.R


internal class LoginActivity : Activity() {
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindFacebookLoginCallback(login_button)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun bindFacebookLoginCallback(loginButton: LoginButton) {
        callbackManager = CallbackManager.Factory.create()
        loginButton.loginBehavior = LoginBehavior.WEB_ONLY
        loginButton.registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onCancel() {
                        this@LoginActivity.runOnUiThread {
                            Toast.makeText(this@LoginActivity, R.string.login_cancelled,
                                    Toast.LENGTH_LONG)
                                    .show()
                        }
                    }

                    override fun onError(exception: FacebookException) {
                        FirebaseCrash.report(exception)
                        this@LoginActivity.runOnUiThread {
                            Toast.makeText(this@LoginActivity, R.string.login_failed,
                                    Toast.LENGTH_LONG)
                                    .show()
                        }
                    }

                    override fun onSuccess(loginResult: LoginResult) {
                        this@LoginActivity.runOnUiThread {
                            val token = getCurrentAccessToken()
                            Toast.makeText(this@LoginActivity,
                                    "Token: ${token.token}\nId: ${token.userId}",
                                    Toast.LENGTH_LONG)
                                    .show()
                        }
                    }
                })
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
