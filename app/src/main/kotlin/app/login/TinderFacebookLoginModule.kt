package app.login

import android.support.v4.widget.ContentLoadingProgressBar
import app.di.PerActivity
import com.facebook.login.widget.LoginButton
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
@PerActivity
internal class TinderFacebookLoginModule(
        private val activity: TinderLoginActivity,
        private val loginButton: LoginButton,
        private val contentLoadingProgressBar: ContentLoadingProgressBar,
        private val tinderFacebookLoginResultCallback: TinderFacebookLoginFeature.ResultCallback,
        private val tinderFacebookLoginCoordinatorResultCallback
        : TinderFacebookLoginCoordinator.ResultCallback) {
    @Provides
    fun feature() = TinderFacebookLoginFeature(loginButton, tinderFacebookLoginResultCallback)

    @Provides
    fun view(): TinderLoginView
            = TinderFacebookLoginView(activity, loginButton, contentLoadingProgressBar)

    @Provides
    fun coordinator(
            view: TinderLoginView,
            postExecutionScheduler: Scheduler)
            = TinderFacebookLoginCoordinator(
            view,
            postExecutionScheduler,
            tinderFacebookLoginCoordinatorResultCallback)
}
