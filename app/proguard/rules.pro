-verbose
-keep class sun.misc.Unsafe { *; }
-dontwarn okhttp3.**
-dontnote okhttp3.**
-dontwarn okio.**
-dontnote okio.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.**
-keepattributes Signature
-keepattributes Exceptions
-dontwarn sun.misc.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.**
-dontwarn android.test.**
-dontwarn kotlin.jvm.internal.Reflection
-dontwarn com.google.errorprone.annotations.*
-dontwarn com.squareup.okhttp.**
-keep class android.support.v7.widget.SearchView { *; }
