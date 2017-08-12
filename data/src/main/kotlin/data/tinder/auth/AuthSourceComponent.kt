package data.tinder.auth

import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AuthSourceModule::class))
@Singleton
internal interface AuthSourceComponent
