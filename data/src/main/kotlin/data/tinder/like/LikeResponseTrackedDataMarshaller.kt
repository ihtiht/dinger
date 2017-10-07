package data.tinder.like

import android.os.Bundle
import tracker.TrackedDataMarshaller
import java.text.SimpleDateFormat
import java.util.Locale

internal class LikeResponseTrackedDataMarshaller : TrackedDataMarshaller<LikeResponse> {
    override fun marshall(source: LikeResponse) = Bundle().apply {
        putString("match", source.match.toString())
        System.currentTimeMillis().let {
            putLong("date", it)
            putString(
                    "date_string",
                    SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH).format(it))
        }
    }
}
