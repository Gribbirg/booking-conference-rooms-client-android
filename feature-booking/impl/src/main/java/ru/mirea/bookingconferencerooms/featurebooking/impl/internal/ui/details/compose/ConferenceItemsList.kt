package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.coreui.components.ErrorScreen
import ru.mirea.bookingconferencerooms.coreui.components.LoadingScreen
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.ConferencesListState
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.ConferencesOfDayState

@Composable
internal fun ConferenceItemsList(
    conferencesOfDayState: ConferencesOfDayState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(conferencesOfDayState.day.toString())
        when (conferencesOfDayState.listState) {
            is ConferencesListState.Loaded -> {
                conferencesOfDayState.listState.conferences.forEach { conference ->
                    ConferenceItem(conference = conference, modifier = Modifier.fillMaxWidth())
                }
            }

            is ConferencesListState.Loading -> {
                LoadingScreen(Modifier.fillMaxSize())
            }

            is ConferencesListState.Error -> {
                ErrorScreen(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun ConferenceItem(
    conference: Conference,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(conference.name)
            Text(conference.authorId)
        }
        Text("${conference.from} - ${conference.to}")
    }
}