package org.mathieu.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.mathieu.data.local.LocationLocal
import org.mathieu.data.local.objects.LocationObject
import org.mathieu.data.local.objects.toModel
import org.mathieu.data.local.objects.toRealmObject
import org.mathieu.data.remote.LocationAPI
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.location.LocationPreview
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository


internal class LocationRepositoryImpl(
    private val context: Context,
    private val locationapi: LocationAPI,
    private val locationLocal: LocationLocal,
) : LocationRepository {

    /**
     * Retrieves the location with the specified ID.
     *
     * The function follows these steps:
     * 1. Tries to fetch the location from the local storage.
     * 2. If not found locally, it fetches the location from the API.
     * 3. Upon successful API retrieval, it saves the location to local storage.
     * 4. If the location is still not found, it throws an exception.
     *
     * @param id The unique identifier of the location to retrieve.
     * @return The [Location] object representing the location details.
     * @throws Exception If the location cannot be found both locally and via the API.
     */
    override suspend fun getLocation(id: Int): Location =
        locationLocal.getLocation(id)?.toModel()
            ?: locationapi.getLocation(id = id)?.let { response ->
                val obj = response.toRealmObject()
                locationLocal.insert(obj)
                obj.toModel()
            }
            ?: throw Exception("Location not found.")


    fun <T> tryOrNull(block: () -> T) = try {
        block()
    } catch (_: Exception) {
        null
    }

    inline fun <T, R> Flow<List<T>>.mapElement(crossinline transform: suspend (value: T) -> R): Flow<List<R>> =
        this.map { list ->
            list.map { element -> transform(element) }
        }
}