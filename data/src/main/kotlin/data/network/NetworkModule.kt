package data.network

import dagger.Module
import dagger.Provides
import org.stoyicker.dinger.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal class NetworkModule {
    @Provides
    @Singleton
    fun retrofitBuilder() = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .validateEagerly(BuildConfig.DEBUG)
}
