package app

import android.app.Application
import data.Data
import domain.Domain

/**
 * Custom application.
 */
internal open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Domain.facadeProvider(Data.facadeProvider())
    }
}
