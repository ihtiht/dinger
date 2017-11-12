package data.tinder.recommendation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        Recommendation.GENDER_MALE,
        Recommendation.GENDER_FEMALE })
@interface Gender {}
