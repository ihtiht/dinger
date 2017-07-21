package app.login

import app.di.PerFeature
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(TinderFacebookLoginModule::class))
@PerFeature
internal interface TinderFacebookLoginComponent {
    fun inject(target: TinderLoginActivity)
}
