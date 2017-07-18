package app.login

import android.support.v4.widget.ContentLoadingProgressBar
import app.ApplicationComponent
import com.facebook.login.widget.LoginButton
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ApplicationComponent::class))
internal class TinderFacebookLoginModule(
        private val loginButton: LoginButton,
        private val contentLoadingProgressBar: ContentLoadingProgressBar,
        private val resultCallback: TinderFacebookLoginFeature.ResultCallback) {
    @Provides
    fun feature() = TinderFacebookLoginFeature(loginButton, resultCallback)

    @Provides
    fun view() = TinderFacebookLoginView(loginButton, contentLoadingProgressBar)

    @Provides
    fun coordinator(view: TinderLoginView, applicationComponent: ApplicationComponent) =
            TinderFacebookLoginCoordinator(view, applicationComponent)
}
