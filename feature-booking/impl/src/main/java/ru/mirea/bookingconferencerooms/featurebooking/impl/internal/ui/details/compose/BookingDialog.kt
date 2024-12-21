package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.compose

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.mirea.bookingconferencerooms.coreui.components.LoadingScreen
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.BookingDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
internal fun BookingDialog(
    state: BookingDialogState,
    onDismiss: () -> Unit,
    onConfirm: (String, OffsetDateTime, OffsetDateTime) -> Unit,
    onRetry: () -> Unit,
) {
    if (state is BookingDialogState.Closed) {
        return
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp)
        ) {
            when (state) {
                BookingDialogState.BusyTimeSelected -> BusyTimeSelectedState(onDismiss, onRetry)
                BookingDialogState.Loading -> LoadingScreen(modifier = Modifier.padding(100.dp))
                is BookingDialogState.Opened -> LoadedState(state, onDismiss, onConfirm)
                BookingDialogState.Closed -> Unit
            }
        }
    }
}

@Composable
private fun LoadedState(
    state: BookingDialogState.Opened,
    onDismiss: () -> Unit,
    onConfirm: (String, OffsetDateTime, OffsetDateTime) -> Unit,
) {
    var name by remember { mutableStateOf(state.name) }
    var startDate by remember { mutableStateOf(state.from.toLocalDate()) }
    var startTime by remember { mutableStateOf(state.from.toLocalTime()) }
    var endDate by remember { mutableStateOf(state.to.toLocalDate()) }
    var endTime by remember { mutableStateOf(state.to.toLocalTime()) }

    val context = LocalContext.current

    val startDatePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                startDate = LocalDate.of(year, month, dayOfMonth)
            },
            startDate.year,
            startDate.monthValue - 1,
            startDate.dayOfMonth,
        )
    }
    val startTimePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                startTime = LocalTime.of(hourOfDay, minute)
            },
            startTime.hour,
            startTime.minute,
            true
        )
    }

    val endDatePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                endDate = LocalDate.of(year, month, dayOfMonth)
            },
            endDate.year,
            endDate.monthValue - 1,
            endDate.dayOfMonth,
        )
    }
    val endTimePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                endTime = LocalTime.of(hourOfDay, minute)
            },
            endTime.hour,
            endTime.minute,
            true
        )
    }


    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Бронирование зала конференций",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Дата от: $startDate")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { startDatePickerDialog.show() }) {
                Text(text = "Выбрать")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Время от: ${startTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { startTimePickerDialog.show() }) {
                Text(text = "Выбрать")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Дата до: $startDate")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { endDatePickerDialog.show() }) {
                Text(text = "Выбрать")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Время до: ${endTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { endTimePickerDialog.show() }) {
                Text(text = "Выбрать")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onConfirm(
                        name,
                        startDate.atTime(startTime.atOffset(ZoneOffset.UTC)),
                        endDate.atTime(endTime.atOffset(ZoneOffset.UTC)),
                    )
                },
                enabled = name.isNotBlank(),
            ) {
                Text("Подтвердить")
            }
        }
    }
}

@Composable
private fun BusyTimeSelectedState(
    onDismiss: () -> Unit,
    onRetry: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Время занято!")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onRetry) {
                Text("Изменить")
            }
        }
    }
}