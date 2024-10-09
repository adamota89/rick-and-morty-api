package org.mathieu.domain.models.location

/**
 * Represents a specific preview location within a universe or dimension.
 *
 * @property id The unique identifier for the location preview.
 * @property name The name of the location preview.
 * @property type The type or category of the location preview.
 * @property dimension The specific dimension or universe where this location preview exists.
 */
data class LocationPreview(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)