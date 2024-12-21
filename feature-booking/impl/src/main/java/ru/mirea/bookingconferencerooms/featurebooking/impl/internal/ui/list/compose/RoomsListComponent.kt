package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomDetails
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.ConferencesListState
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.ConferencesOfDayState
import java.time.format.DateTimeFormatter
import java.util.UUID

@Composable
internal fun RoomsList(
    rooms: List<ConferenceRoom>,
    modifier: Modifier = Modifier,
    onItemClicked: (UUID) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(rooms, key = { room -> room.id }) { room ->
            RoomItem(
                room = room,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onItemClicked(room.id) },
            )
        }
    }
}

@Composable
private fun RoomItem(room: ConferenceRoom, modifier: Modifier = Modifier) {
    val formattedTime = room.freeTo.format(DateTimeFormatter.ofPattern("HH:mm"))
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Room: ${room.name}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Floor: ${room.floor}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Max People: ${room.maxPeople}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = "Free until: $formattedTime",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
