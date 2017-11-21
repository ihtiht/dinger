package data.network

import com.squareup.moshi.JsonQualifier

// TODO Add support for name()
@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class JsonObjectOrFalse

