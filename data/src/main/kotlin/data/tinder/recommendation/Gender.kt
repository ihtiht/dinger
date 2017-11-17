package data.tinder.recommendation

import android.support.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(Recommendation.GENDER_MALE, Recommendation.GENDER_FEMALE)
annotation class Gender
