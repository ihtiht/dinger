package data

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import data.account.AccountComponentHolder
import data.account.AccountModule
import data.account.AppAccountManagerImpl
import data.account.DaggerAccountComponent
import data.alarm.AppAlarmManagerImpl
import data.autoswipe.AutoSwipeComponentHolder
import data.autoswipe.AutoSwipeLauncherFactoryImpl
import data.autoswipe.DaggerAutoSwipeComponent
import data.network.FacadeProviderImpl
import data.tinder.DaggerTinderRepositoryComponent
import data.tinder.TinderRepositoryComponentHolder
import data.tinder.like.LikeRecommendationProviderImpl
import data.tinder.recommendation.GetRecommendationProviderImpl
import domain.DomainHolder
import domain.alarm.AlarmHolder
import domain.auth.AuthHolder
import domain.autoswipe.AutoSwipeHolder
import domain.like.LikeRecommendationHolder
import domain.recommendation.GetRecommendationHolder
import javax.inject.Inject

/**
 * @see <a href="https://firebase.googleblog.com/2016/12/how-does-firebase-initialize-on-android.html">
 *     The Firebase Blog: How does Firebase initialize on Android</a>
 */
internal class InitializationContentProvider : ContentProvider() {
    @Inject
    lateinit var facadeProviderImpl: FacadeProviderImpl
    @Inject
    lateinit var getRecommendationProviderImpl: GetRecommendationProviderImpl
    @Inject
    lateinit var likeRecommendationProviderImpl: LikeRecommendationProviderImpl
    @Inject
    lateinit var accountManagerImpl: AppAccountManagerImpl
    @Inject
    lateinit var alarmManagerImpl: AppAlarmManagerImpl
    @Inject
    lateinit var autoSwipeIntentFactoryImpl: AutoSwipeLauncherFactoryImpl

    override fun onCreate(): Boolean {
        val rootModule = RootModule(context)
        val accountModule = AccountModule()
        AccountComponentHolder.accountComponent = DaggerAccountComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .build()
        TinderRepositoryComponentHolder.tinderRepositoryComponent =
                DaggerTinderRepositoryComponent.builder()
                        .rootModule(rootModule)
                        .accountModule(accountModule)
                        .build()
        AutoSwipeComponentHolder.autoSwipeComponent = DaggerAutoSwipeComponent.builder()
                .rootModule(rootModule)
                .build()
        DaggerInitializationComponent.builder()
                .rootModule(rootModule)
                .accountModule(accountModule)
                .build()
                .inject(this)
        DomainHolder.facadeProvider(facadeProviderImpl)
        GetRecommendationHolder.getRecommendationProvider(getRecommendationProviderImpl)
        LikeRecommendationHolder.likeRecommendationProvider(likeRecommendationProviderImpl)
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
    override fun getType(uri: Uri?) = "vnd.android.cursor.item.none"
}
