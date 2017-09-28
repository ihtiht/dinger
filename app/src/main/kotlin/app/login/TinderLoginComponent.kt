package app.login

import app.di.PerActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(TinderLoginModule::class))
@PerActivity
internal interface TinderLoginComponent {
    fun inject(target: TinderLoginActivity)
}
