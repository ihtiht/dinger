package data.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ParserModule {
    @Provides
    @Singleton
    fun kotlinMoshiBuilder(): Moshi.Builder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
}
