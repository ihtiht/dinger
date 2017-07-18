package app.login

import dagger.Component

@Component(modules = arrayOf(TinderFacebookLoginModule::class))
internal interface TinderFacebookLoginComponent {
    fun inject(target: TinderLoginActivity)
}
