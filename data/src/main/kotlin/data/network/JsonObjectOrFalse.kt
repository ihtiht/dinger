package data.network

import com.squareup.moshi.JsonQualifier

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class JsonObjectOrFalse

