package data

import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(RootModule::class))
@Singleton
internal interface RootComponent
