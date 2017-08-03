package app.alarmbanner

import app.di.PerActivity
import app.login.TinderFacebookLoginModule
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AlarmBannerModule::class))
@PerActivity
internal interface AlarmBannerComponent {
    fun inject(target: AlarmBannerActivity)
}
