package data.network.tinder.recommendations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@IntDef({
        DataRecommendationUser.GENDER_MALE,
        DataRecommendationUser.GENDER_FEMALE })
public @interface Gender { }
