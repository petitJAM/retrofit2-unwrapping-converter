package dev.petitjam.retrofit2.converter.unwrapping

import retrofit2.Retrofit

/**
 * Annotate a [Retrofit] interface method to trigger the [UnwrappingConverterFactory] to unwrap the JSON response under
 * the given [unwrapKey].
 *
 * @param unwrapKey the top level key containing the JSON object or array you want to unwrap
 */
annotation class Unwrap(
    val unwrapKey: String,
)