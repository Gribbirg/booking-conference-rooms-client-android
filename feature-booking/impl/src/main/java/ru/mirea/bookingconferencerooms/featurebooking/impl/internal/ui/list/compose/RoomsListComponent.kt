package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
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
                    .padding(all = 4.dp)
                    .clickable { onItemClicked(room.id) },
            )
        }
    }
}

@Composable
private fun RoomItem(room: ConferenceRoom, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Room: ${room.name}",
                style = MaterialTheme.typography.bodyMedium
            )

            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Floor: ${room.floor}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Max People: ${room.maxPeople}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
