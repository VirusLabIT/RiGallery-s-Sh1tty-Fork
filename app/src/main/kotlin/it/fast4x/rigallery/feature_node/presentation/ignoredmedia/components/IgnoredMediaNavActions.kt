package it.fast4x.rigallery.feature_node.presentation.ignoredmedia.components

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.stringResource
import it.fast4x.rigallery.R
import it.fast4x.rigallery.core.Constants.Animation.enterAnimation
import it.fast4x.rigallery.core.Constants.Animation.exitAnimation
import it.fast4x.rigallery.feature_node.domain.model.Media
import it.fast4x.rigallery.feature_node.domain.model.MediaState

@Composable
fun <T: Media> IgnoredMediaNavActions(
    toggleFavorite: (ActivityResultLauncher<IntentSenderRequest>, List<T>, Boolean) -> Unit,
    mediaState: State<MediaState<T>>,
    selectedMedia: SnapshotStateList<T>,
    selectionState: MutableState<Boolean>,
    result: ActivityResultLauncher<IntentSenderRequest>
) {
    val removeAllTitle = stringResource(R.string.remove_all)
    val removeSelectedTitle = stringResource(R.string.remove_selected)
    val title = if (selectionState.value) removeSelectedTitle else removeAllTitle
    AnimatedVisibility(
        visible = mediaState.value.media.isNotEmpty(),
        enter = enterAnimation,
        exit = exitAnimation
    ) {
        TextButton(
            onClick = {
                toggleFavorite(result, selectedMedia.ifEmpty { mediaState.value.media }, false)
            }
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

