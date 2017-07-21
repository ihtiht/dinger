package app.splash

import app.di.PerActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(LoggedInCheckModule::class))
@PerActivity
internal interface LoggedInCheckComponent {
    fun inject(target: SplashActivity)
}
