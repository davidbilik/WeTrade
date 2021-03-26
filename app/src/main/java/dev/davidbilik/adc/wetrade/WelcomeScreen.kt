package dev.davidbilik.adc.wetrade

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dev.davidbilik.adc.wetrade.theme.ui.WeTradeTheme
import dev.davidbilik.adc.wetrade.theme.ui.Yellow

@Composable
fun WelcomeScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            Background()
            Logo(modifier = Modifier.align(Alignment.Center))
            ButtonsRow(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun Background() {
    Image(
        painter = painterResource(id = R.drawable.welcome_bg),
        contentDescription = "bg",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight
    )
}

@Composable
private fun Logo(modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo", modifier = modifier)
}

@Composable
private fun ButtonsRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val navController = findNavController()
        PrimaryButton(
            text = "Get Started",
            modifier = Modifier.weight(1f),
            onClick = {
                navController.navigate("home")
            }
        )
        LoginButton(
            modifier = Modifier.weight(1f)
        ) {
            navController.navigate("login")
        }
    }
}

@Composable
private fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier
            .height(48.dp),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = Yellow),
        onClick = onClick
    ) {
        Text("Log In".toUpperCase())
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewWelcomeScreen() {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        WeTradeTheme {
            WelcomeScreen()
        }
    }
}
