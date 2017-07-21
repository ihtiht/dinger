package data

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import data.auth.DingerAccountManager
import data.network.FacadeProviderImpl
import domain.Domain

/**
 * Used to tie to the app lifecycle, for things like obtaining a Context reference or initializing
 * DI.
 * @see <a href="https://firebase.googleblog.com/2016/12/how-does-firebase-initialize-on-android.html">
 *     The Firebase Blog: How does Firebase initialize on Android</a>
 */
internal class InitializationContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        throw IllegalAccessError("I'm where I should be")
        DingerAccountManager.context = context
        Domain.apply {
            facadeProvider(FacadeProviderImpl)
            accountManager(DingerAccountManager)
        }
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
