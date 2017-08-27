package app.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.MainApplication
import app.alarmbanner.AlarmBannerActivity
import kotlinx.android.synthetic.main.activity_login.login_button
import kotlinx.android.synthetic.main.activity_login.progress
import org.stoyicker.dinger.R
import javax.inject.Inject

internal class TinderLoginActivity
    : TinderFacebookLoginFeature.ResultCallback,
        TinderFacebookLoginCoordinator.ResultCallback,
        Activity()  {
    @Inject
    lateinit var tinderFacebookLoginFeature: TinderFacebookLoginFeature
    @Inject
    lateinit var tinderFacebookLoginCoordinator: TinderFacebookLoginCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inject()
        tinderFacebookLoginFeature.bind()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tinderFacebookLoginFeature.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        tinderFacebookLoginFeature.release(login_button)
        tinderFacebookLoginCoordinator.actionCancelLogin()
        super.onDestroy()
    }

    override fun onSuccess(facebookId: String, facebookToken: String) {
        tinderFacebookLoginCoordinator.actionDoLogin(facebookId, facebookToken)
    }

    override fun onTinderLoginSuccess() {
        AlarmBannerActivity.getCallingIntent(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
        finishAfterTransition()
    }

    private fun inject() = (application as MainApplication).applicationComponent
            .newTinderFacebookLoginComponent(
                    TinderFacebookLoginModule(this, login_button, progress, this, this))
            .inject(this)

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, TinderLoginActivity::class.java)
    }
}
