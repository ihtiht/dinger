package data.tinder

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.stoyicker.dinger.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import javax.inject.Singleton

@Module
internal class TinderApiModule {
    @Provides
    @Singleton
    fun retrofit() = Retrofit.Builder()
            .baseUrl(TinderApi.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .validateEagerly(BuildConfig.DEBUG)
            .client(OkHttpClient.Builder()
                    .addInterceptor {
                        it.proceed(it.request().newBuilder()
                                .apply {
                                    //TODO("if logged-in")
                                    addHeader(TinderApi.HEADER_AUTH, "TODO Retrieve the token from somewhere")
                                }.build())
                    }
                    .build())
            .build()
}
