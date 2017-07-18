package app.login

import android.view.View

internal class TinderFacebookLoginView(
        private val loginButton: View,
        private val progress: View)
    : TinderLoginView {
    override fun setRunning() {
        loginButton.visibility = View.INVISIBLE
        progress.visibility = View.VISIBLE
    }

    override fun setStale() {
        loginButton.visibility = View.VISIBLE
        progress.visibility = View.INVISIBLE
    }
}
