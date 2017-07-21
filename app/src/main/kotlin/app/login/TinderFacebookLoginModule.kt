package app.login

import android.support.v4.widget.ContentLoadingProgressBar
import app.di.PerActivity
import com.facebook.login.widget.LoginButton
import dagger.Module
import dagger.Provides
import domain.exec.PostExecutionSchedulerProvider

@Module
@PerActivity
internal class TinderFacebookLoginModule(
        private val loginButton: LoginButton,
        private val contentLoadingProgressBar: ContentLoadingProgressBar,
        private val callback: TinderFacebookLoginFeature.ResultCallback) {
    @Provides
    fun feature() = TinderFacebookLoginFeature(loginButton, callback)

    @Provides
    fun view(): TinderLoginView = TinderFacebookLoginView(loginButton, contentLoadingProgressBar)

    @Provides
    fun coordinator(
            view: TinderLoginView,
            postExecutionSchedulerProvider: PostExecutionSchedulerProvider) =
            TinderFacebookLoginCoordinator(view, postExecutionSchedulerProvider)
}
