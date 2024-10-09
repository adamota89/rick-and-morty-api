package org.mathieu.locations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import org.mathieu.characters.details.CharacterDetailsState
import org.mathieu.characters.list.CharactersAction
import org.mathieu.domain.models.character.Character
import org.mathieu.ui.theme.Purple40

private typealias UIState = LocationDetailsState

@Composable
fun LocationDetailsScreen(
    navController: NavController,
    id: Int
) {
    val viewModel: LocationDetailsViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    viewModel.init(locationId = id)

    LocationDetailsContent(
        state = state,
        onClickBack = navController::popBackStack,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
private fun LocationDetailsContent(
    state: UIState = UIState(),
    onClickBack: () -> Unit = {}
) {
    Scaffold(topBar = {

        Row(
            modifier = Modifier
                .background(org.mathieu.ui.theme.Purple40)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = onClickBack),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )

            Text(
                text = state.location?.name ?: state.error.toString(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp), // Espacement entre les éléments
                horizontalAlignment = Alignment.CenterHorizontally // Centrer horizontalement les éléments
            ) {
                Row(
                    modifier = Modifier
                        .shadow(5.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = state.location?.type ?: state.error ?: "Type non disponible",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    modifier = Modifier
                        .shadow(5.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = state.location?.dimension ?: state.error ?: "Dimension non disponible",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}