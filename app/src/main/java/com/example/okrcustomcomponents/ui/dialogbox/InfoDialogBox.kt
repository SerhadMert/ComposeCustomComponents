package com.example.okrcustomcomponents.ui.dialogbox

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.*
import com.example.okrcustomcomponents.R

@Composable
fun InfoDialogBox(
    title: String? = "Message",
    desc: String? = "Your Message",
    iteration: Int? = LottieConstants.IterateForever,
    lottie: Int? = R.raw.loading_lottie,
    dismissOnOutside: Boolean = true,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnOutside,
            dismissOnClickOutside = dismissOnOutside
        )
    ) {
        Box(
            modifier = Modifier
                .heightIn(200.dp, 550.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                iteration?.let { iteration ->
                    lottie?.let { lottie ->
                        HeaderLoadingImage(
                            modifier = Modifier
                                .size(200.dp)
                                .border(
                                    border = BorderStroke(width = 5.dp, color = Color.White),
                                    shape = CircleShape
                                ),
                            iteration = iteration,
                            lottie = lottie
                        )
                    }
                }

                Text(
                    text = title.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = desc.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(24.dp))

                ElevatedButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    Text(
                        text = stringResource(R.string.dialog_box_button_text),
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderLoadingImage(modifier: Modifier, iteration: Int, lottie: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lottie))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iteration
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}