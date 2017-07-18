package app

import app.login.TinderFacebookLoginCoordinator
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
internal interface ApplicationComponent {
    fun inject(target: TinderFacebookLoginCoordinator)
}
