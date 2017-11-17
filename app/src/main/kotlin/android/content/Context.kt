package android.content

internal fun Context.versionCode() = packageManager.getPackageInfo(packageName, 0).versionCode
