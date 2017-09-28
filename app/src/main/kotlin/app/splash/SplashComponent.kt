package app.splash

import app.di.PerActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SplashModule::class))
@PerActivity
internal interface SplashComponent {
    fun inject(target: SplashActivity)
}
