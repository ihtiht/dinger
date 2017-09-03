package data

import android.content.Context
import dagger.Module
import dagger.Provides
import data.database.AppDatabase
import javax.inject.Singleton

@Module
internal class RootModule(private val context: Context) {
    @Provides
    @Singleton
    fun context() = context
}
