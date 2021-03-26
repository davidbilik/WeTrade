package dev.davidbilik.adc.wetrade

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(48.dp),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
    ) {
        Text(text.toUpperCase())
    }
}
