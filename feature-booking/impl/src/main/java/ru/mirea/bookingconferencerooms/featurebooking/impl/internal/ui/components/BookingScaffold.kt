package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BookingScaffold(
    modifier: Modifier = Modifier,
    title: String = "",
    imageUrl: String? = null,
    onBack: (() -> Unit)? = null,
    toProfile: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    onBack?.let {
                        IconButton(
                            onClick = onBack,
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                },
                actions = {
                    imageUrl?.let {
                        IconButton(
                            onClick = toProfile,
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape),
                                model = it,
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        },
        content = content,
    )
}