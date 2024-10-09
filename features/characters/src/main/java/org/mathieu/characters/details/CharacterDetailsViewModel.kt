package org.mathieu.characters.details

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.characters.list.CharactersAction
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.location.LocationPreview
import org.mathieu.ui.Destination
import org.mathieu.ui.ViewModel

sealed interface LocationAction {
    data class SelectedLocation(val location: Int?):
        LocationAction
}

class CharacterDetailsViewModel(application: Application) : org.mathieu.ui.ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

    private val characterRepository: org.mathieu.domain.repositories.CharacterRepository by inject()

    fun init(characterId: Int) {

        fetchData(
            source = { characterRepository.getCharacter(id = characterId) }
        ) {

            onSuccess {
                updateState {
                    copy(avatarUrl = it.avatarUrl, name = it.name, error = null, location = it.locationPreviews
                    )
                }
            }

            onFailure { error ->
                updateState { copy(error = error.toString()) }
            }
            updateState { copy(isLoading = false) }
        }
    }

    fun handleAction(action: LocationAction) {
        when(action) {
            is LocationAction.SelectedLocation -> SelectedLocation(action.location)
        }
    }

    private fun SelectedLocation(locationId: Int?) =
        sendEvent(Destination.LocationDetails(locationId.toString()))
}

// Mise à jour du data class pour l'état
data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val error: String? = null,
    val location: LocationPreview? = null
)
