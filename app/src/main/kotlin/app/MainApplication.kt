package app

import android.app.Application
import domain.Domain

/**
 * Custom application.
 */
internal open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Domain.facadeProvider(data.Data.facadeProvider())
    }
}
