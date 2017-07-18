package app.login

import android.support.v4.widget.ContentLoadingProgressBar
import com.facebook.login.widget.LoginButton
import dagger.Module
import dagger.Provides

@Module
internal class TinderFacebookLoginModule(
        private val contentLoadingProgressBar: ContentLoadingProgressBar,
        private val loginButton: LoginButton,
        private val resultCallback: TinderFacebookLoginFeature.ResultCallback) {
    @Provides
    fun feature() = TinderFacebookLoginFeature(loginButton, resultCallback)

    @Provides
    fun view() = TinderLoginViewImpl(contentLoadingProgressBar)

    @Provides
    fun coordinator(view: TinderLoginViewImpl) = TinderFacebookLoginCoordinator(view)
}
