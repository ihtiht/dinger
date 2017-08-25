package data.tinder.recommendation

import android.support.annotation.Px

internal class ResolvedRecommendationProcessedFile(
        @Px
        val widthPx: Int,
        val url: String,
        @Px
        val heightPx: Int) {
    companion object {
        val NONE = ResolvedRecommendationProcessedFile(0, "", 0)
    }
}
