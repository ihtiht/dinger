package app

import android.app.Application
import data.Data
import data.InAppAccountManager
import domain.Domain

/**
 * Custom application.
 */
internal open class MainApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }

    override fun onCreate() {
        super.onCreate()
        InAppAccountManager.context = applicationContext
        Domain.facadeProvider(Data.facadeProvider())
    }
}
