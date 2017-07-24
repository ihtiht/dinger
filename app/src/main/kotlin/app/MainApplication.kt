package app

import android.app.Application
import app.di.ApplicationComponent
import app.di.DaggerApplicationComponent

/**
 * Custom application.
 */
internal open class MainApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }
}
