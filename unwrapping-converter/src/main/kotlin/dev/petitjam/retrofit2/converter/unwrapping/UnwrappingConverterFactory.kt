package dev.petitjam.retrofit2.converter.unwrapping

import com.squareup.moshi.JsonReader
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.lang.reflect.Type

/**
 * A [Converter.Factory] which generates a [Converter] that unwraps the JSON response data.
 *
 *
 */
class UnwrappingConverterFactory(
    private val moshiConverterFactory: MoshiConverterFactory,
) : Converter.Factory() {

    companion object {
        private val jsonMediaType = "application/json".toMediaType()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *>? {
        val unwrapAnnotation = annotations.firstOrNull { it.annotationClass == Unwrap::class }
        if (unwrapAnnotation != null && unwrapAnnotation is Unwrap) {
            val unwrapKey = unwrapAnnotation.unwrapKey
            return Converter { responseBody ->
                val unwrappedResponseBody = unwrapResponseBody(responseBody, unwrapKey)
                return@Converter moshiConverterFactory
                    .responseBodyConverter(type, annotations, retrofit)
                    ?.convert(unwrappedResponseBody)
            }
        }
        return null
    }

    private fun unwrapResponseBody(responseBody: ResponseBody, unwrapKey: String): ResponseBody {
        val reader = JsonReader.of(responseBody.source())

        with(reader) {
            beginObject()
            while (hasNext()) {
                when (selectName(JsonReader.Options.of(unwrapKey))) {
                    0 -> {
                        // This is a source containing what's under unwrapKey
                        return@unwrapResponseBody nextSource().asResponseBody(jsonMediaType)
                    }
                    -1 -> {
                        // I think this is some other top level key not matching unwrapKey
                    }
                }
            }
        }

        // TODO: improve error messaging
        throw IOException("Unable to unwrap object")
    }
}
