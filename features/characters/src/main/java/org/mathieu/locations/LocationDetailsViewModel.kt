package org.mathieu.locations

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.characters.details.CharacterDetailsState
import org.mathieu.characters.details.LocationAction
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.location.LocationPreview
import org.mathieu.ui.Destination

class LocationDetailsViewModel(application: Application) : org.mathieu.ui.ViewModel<LocationDetailsState>(
    LocationDetailsState(), application) {


    private val locationRepository: org.mathieu.domain.repositories.LocationRepository by inject()

    fun init(locationId: Int) {

        fetchData(
            source = { locationRepository.getLocation(id = locationId) }
        ) {

            onSuccess {
                updateState {
                    copy(error = null, location = it
                    )
                }
            }

            onFailure { error ->
                updateState { copy(error = error.toString()) }
            }
            updateState { copy(isLoading = false) }
        }
    }
}

// Mise à jour du data class pour l'état
data class LocationDetailsState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val location: Location? = null
)
