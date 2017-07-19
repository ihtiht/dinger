package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.login_button
import kotlinx.android.synthetic.main.activity_login.progress
import org.stoyicker.dinger.R
import javax.inject.Inject

internal class TinderLoginActivity : Activity(), TinderFacebookLoginFeature.ResultCallback {
    @Inject
    lateinit var tinderFacebookLoginFeature: TinderFacebookLoginFeature
    @Inject
    lateinit var coordinatorFacebook: TinderFacebookLoginCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inject()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tinderFacebookLoginFeature.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        unbindFacebookLoginFeature()
        cancelOngoingTinderLogin()
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

    override fun onSuccess(facebookId: String, facebookToken: String) {
        requestTinderLogin(facebookId, facebookToken)
    }

    private fun inject() {
        DaggerTinderFacebookLoginComponent
                .builder()
                .tinderFacebookLoginModule(TinderFacebookLoginModule(login_button, progress, this))
                .build()
    }

    private fun unbindFacebookLoginFeature() = tinderFacebookLoginFeature.release(login_button)

    private fun requestTinderLogin(facebookId: String, facebookToken: String) =
            coordinatorFacebook.actionDoLogin(facebookId, facebookToken)

    private fun cancelOngoingTinderLogin() = coordinatorFacebook.actionCancelLogin()

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, TinderLoginActivity::class.java)
    }
}
