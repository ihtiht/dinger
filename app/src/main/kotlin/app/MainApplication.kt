package app

import android.app.Application

/**
 * Custom application.
 */
internal open class MainApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }
}
