package app

import app.login.TinderFacebookLoginComponent
import app.login.TinderFacebookLoginModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
internal interface ApplicationComponent {
    fun newTinderFacebookLoginComponent(tinderFacebookLoginModule: TinderFacebookLoginModule):
            TinderFacebookLoginComponent
}
