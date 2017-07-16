package data

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri

internal class InitializationContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
//        ComponentHolder.topPostsFacadeComponent = DaggerTopPostsFacadeComponent.create()
//        ComponentHolder.topRequestSourceComponent = DaggerTopRequestSourceComponent.builder()
//                .topRequestSourceModule(TopRequestSourceModule(context.cacheDir))
//                .build()
        return true
    }

    override fun insert(uri: Uri?, values: ContentValues?) = null
    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?,
                       selectionArgs: Array<out String>?, sortOrder: String?) = null
    override fun update(uri: Uri?, values: ContentValues?, selection: String?,
                        selectionArgs: Array<out String>?) = 0
    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?) = 0
    override fun getType(uri: Uri?) = null
}
