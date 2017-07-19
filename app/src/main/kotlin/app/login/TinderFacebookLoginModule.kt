package app.login

import android.support.v4.widget.ContentLoadingProgressBar
import app.ApplicationModule
import com.facebook.login.widget.LoginButton
import dagger.Module
import dagger.Provides
import domain.exec.PostExecutionSchedulerProvider
import javax.inject.Singleton

@Module(includes = arrayOf(ApplicationModule::class))
@Singleton
internal class TinderFacebookLoginModule(
        private val loginButton: LoginButton,
        private val contentLoadingProgressBar: ContentLoadingProgressBar,
        private val resultCallback: TinderFacebookLoginFeature.ResultCallback) {
    @Provides
    fun feature() = TinderFacebookLoginFeature(loginButton, resultCallback)

    @Provides
    fun view(): TinderLoginView = TinderFacebookLoginView(loginButton, contentLoadingProgressBar)

    @Provides
    fun coordinator(
            view: TinderLoginView,
            postExecutionSchedulerProvider: PostExecutionSchedulerProvider) =
            TinderFacebookLoginCoordinator(view, postExecutionSchedulerProvider)
}
