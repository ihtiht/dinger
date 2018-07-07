package app

import android.annotation.SuppressLint
import android.app.Application
import app.di.ApplicationComponent
import app.di.DaggerApplicationComponent

/**
 * Custom application.
 */
@SuppressLint("Registered") // It is registered in the buildtype-specific manifests
internal open class MainApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }
}
