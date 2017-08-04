package app.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
@Singleton
internal class ApplicationModule {
    @Provides
    @Singleton
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
