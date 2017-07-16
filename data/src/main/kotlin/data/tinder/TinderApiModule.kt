package data.tinder

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.stoyicker.dinger.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal class TinderApiModule {
    @Provides
    @Singleton
    fun retrofit() = Retrofit.Builder()
            .baseUrl(TinderApi.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
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
            .create(TinderApi::class.java)
}
