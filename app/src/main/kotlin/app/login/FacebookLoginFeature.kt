package app.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.crash.FirebaseCrash
import org.stoyicker.dinger.R

internal class FacebookLoginFeature(context: Context, loginButton: LoginButton) {
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()

    init {
        loginButton.apply {
            loginBehavior = LoginBehavior.WEB_ONLY
            registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onCancel() {
                            Toast.makeText(context, R.string.login_cancelled,
                                    Toast.LENGTH_LONG)
                                    .show()
                        }

                        override fun onError(exception: FacebookException) {
                            FirebaseCrash.report(exception)
                            Toast.makeText(context, R.string.login_failed,
                                    Toast.LENGTH_LONG)
                                    .show()
                        }

                        override fun onSuccess(loginResult: LoginResult) {
                            val token = AccessToken.getCurrentAccessToken()
                            Toast.makeText(context,
                                    "Token: ${token.token}\nId: ${token.userId}",
                                    Toast.LENGTH_LONG)
                                    .show()
                        }
                    })
        }
    }

    /**
     * Call from Activity#onActivityResult(Int, Int, Intent?).
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun release(loginButton: LoginButton) {
        loginButton.unregisterCallback(callbackManager)
    }
}
