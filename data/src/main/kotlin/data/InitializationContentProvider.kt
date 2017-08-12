package data

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import data.account.AccountModule
import data.account.AppAccountManagerImpl
import data.account.DaggerAccountComponent
import data.alarm.AppAlarmManagerImpl
import data.autoswipe.AutoSwipeLauncherFactoryImpl
import data.network.FacadeProviderImpl
import data.network.NetworkModule
import data.tinder.DaggerTinderRepositoryComponent
import data.tinder.auth.AuthFacadeModule
import data.tinder.auth.AuthSourceModule
import data.tinder.recommendation.RecommendationFacadeModule
import data.tinder.recommendation.RecommendationSourceModule
import domain.DomainHolder
import domain.alarm.AlarmHolder
import domain.auth.AuthHolder
import domain.autoswipe.AutoSwipeHolder
import javax.inject.Inject

/**
 * @see <a href="https://firebase.googleblog.com/2016/12/how-does-firebase-initialize-on-android.html">
 *     The Firebase Blog: How does Firebase initialize on Android</a>
 */
internal class InitializationContentProvider : ContentProvider() {
    @Inject
    lateinit var facadeProviderImpl: FacadeProviderImpl
    @Inject
    lateinit var accountManagerImpl: AppAccountManagerImpl
    @Inject
    lateinit var alarmManagerImpl: AppAlarmManagerImpl
    @Inject
    lateinit var autoSwipeIntentFactoryImpl: AutoSwipeLauncherFactoryImpl

    override fun onCreate(): Boolean {
        val rootModule = RootModule(context)
        val accountModule = AccountModule()
        data.ComponentHolder.accountComponent = DaggerAccountComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .build()
        data.ComponentHolder.tinderRepositoryComponent = DaggerTinderRepositoryComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .authSourceModule(AuthSourceModule())
                .authFacadeModule(AuthFacadeModule())
                .recommendationSourceModule(RecommendationSourceModule())
                .recommendationFacadeModule(RecommendationFacadeModule())
                .build()
        DaggerInitializationComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .networkModule(NetworkModule())
                .build().inject(this)
        DomainHolder.apply {
            facadeProvider(facadeProviderImpl)
        }
        AuthHolder.accountManager(accountManagerImpl)
        AlarmHolder.alarmManager(alarmManagerImpl)
        AutoSwipeHolder.autoSwipeIntentFactory(autoSwipeIntentFactoryImpl)
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
