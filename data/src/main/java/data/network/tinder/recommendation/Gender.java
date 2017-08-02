package data.network.tinder.recommendation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RecommendationUser.GENDER_MALE,
        RecommendationUser.GENDER_FEMALE })
@interface Gender { }
