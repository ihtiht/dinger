package app.login

import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(TinderFacebookLoginModule::class))
@Singleton
internal interface TinderFacebookLoginComponent {
    fun inject(target: TinderLoginActivity)
}
