package data.tinder.recommendation

import android.support.annotation.Px

internal data class ResolvedRecommendationProcessedFile(
        @Px
        val widthPx: Int,
        val url: String,
        @Px
        val heightPx: Int) {
    companion object {
        val NONE = ResolvedRecommendationProcessedFile(widthPx = 0, url = "", heightPx = 0)
    }
}
