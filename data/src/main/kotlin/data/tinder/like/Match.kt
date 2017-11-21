package data.tinder.like

import com.squareup.moshi.Json

// A bunch of other fields come in the model. Add them as needed
internal class Match private constructor(@field:Json(name = "id") private val id: String)
