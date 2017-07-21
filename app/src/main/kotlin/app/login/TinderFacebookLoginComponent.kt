package app.login

import app.di.PerActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(TinderFacebookLoginModule::class))
@PerActivity
internal interface TinderFacebookLoginComponent {
    fun inject(target: TinderLoginActivity)
}
