package data

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import data.account.AccountModule
import data.account.DaggerAccountComponent
import data.account.DingerAccountManager
import data.network.FacadeProviderImpl
import data.network.tinder.auth.AuthFacadeModule
import data.network.tinder.auth.AuthSourceModule
import data.network.tinder.auth.DaggerAuthFacadeComponent
import domain.Domain
import javax.inject.Inject

/**
 * Used to tie to the app lifecycle, for things like obtaining a Context reference or initializing
 * DI.
 * @see <a href="https://firebase.googleblog.com/2016/12/how-does-firebase-initialize-on-android.html">
 *     The Firebase Blog: How does Firebase initialize on Android</a>
 */
internal class InitializationContentProvider : ContentProvider() {
    @Inject
    lateinit var accountManager: DingerAccountManager

    override fun onCreate(): Boolean {
        val rootModule = RootModule(context)
        val accountModule = AccountModule()
        ComponentHolder.accountComponent = DaggerAccountComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .build()
        ComponentHolder.authFacadeComponent = DaggerAuthFacadeComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .authSourceModule(AuthSourceModule())
                .authFacadeModule(AuthFacadeModule())
                .build()
        ComponentHolder.accountComponent.inject(this)
        Domain.apply {
            facadeProvider(FacadeProviderImpl)
            accountManager(accountManager)
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
