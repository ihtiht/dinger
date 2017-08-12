package data.tinder.recommendation

import android.arch.persistence.room.TypeConverter
import com.squareup.moshi.Moshi

internal class RecommendationTypeConverters private constructor() {
    companion object {
        private val moshi by lazy { Moshi.Builder().build() }

        @TypeConverter
        fun recommendationUserPhotoProcessedFileEntity(
                source: RecommendationUserPhotoProcessedFileEntity)
        = toJson(source, { RecommendationUserPhotoProcessedFile(it.widthPx, it.url, it.heightPx) })

        @TypeConverter
        fun recommendationUserPhotoProcessedFileEntity(source: String)
        = fromJson<RecommendationUserPhotoProcessedFileEntity,
                RecommendationUserPhotoProcessedFile>(source,
                { RecommendationUserPhotoProcessedFileEntity(it.widthPx, it.url, it.heightPx) })

        @TypeConverter
        fun recommendationUserInstagramPhotoEntity(
                source: RecommendationUserInstagramPhotoEntity)
        = toJson(source, { RecommendationUserInstagramPhoto(
                it.link, it.imageUrl, it.thumbnailUrl, it.ts) })

        @TypeConverter
        fun recommendationUserInstagramPhotoEntity(source: String)
        = fromJson<RecommendationUserInstagramPhotoEntity,
                RecommendationUserInstagramPhoto>(source,
                { RecommendationUserInstagramPhotoEntity(
                        it.link, it.imageUrl, it.thumbnailUrl, it.ts) })

        private inline fun <EntityType, reified JsonType> toJson(
                source: EntityType,
                constructor: (EntityType)-> JsonType): String
                = moshi.adapter(JsonType::class.java).toJson(constructor(source))

        private inline fun <EntityType, reified JsonType> fromJson(
                source: String,
                constructor: (JsonType) -> EntityType): EntityType?
                = moshi.adapter(JsonType::class.java).fromJson(source)?.let { constructor(it) }
    }
}
