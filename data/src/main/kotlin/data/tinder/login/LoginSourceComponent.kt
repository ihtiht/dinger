package data.tinder.login

import dagger.Component
import javax.inject.Singleton

@Component(modules = [LoginSourceModule::class])
@Singleton
internal interface LoginSourceComponent
