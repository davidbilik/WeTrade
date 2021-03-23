package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.Gray900
import com.example.androiddevchallenge.ui.theme.WeTradeTheme
import com.example.androiddevchallenge.ui.theme.White

@Composable
fun LoginScreen() {
    val bgColor = if (MaterialTheme.colors.isLight) {
        White
    } else {
        Gray900
    }
    Surface(color = bgColor) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header()

            Spacer(modifier = Modifier.height(40.dp))

            EmailInput()

            Spacer(modifier = Modifier.height(8.dp))

            PasswordInput()

            Spacer(modifier = Modifier.height(16.dp))

            LoginButton()
        }
    }
}

@Composable
private fun LoginButton() {
    PrimaryButton(
        text = "Log In", modifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

    }
}

@Composable
private fun PasswordInput() {
    var passwordValue by remember {
        mutableStateOf("")
    }
    InputField(
        value = passwordValue,
        onValueChange = { passwordValue = it },
        icon = Icons.Default.Password,
        placeholder = "Password",
        keyboardType = KeyboardType.Password
    )
}

@Composable
private fun EmailInput() {
    var emailValue by remember {
        mutableStateOf("")
    }
    InputField(
        value = emailValue,
        onValueChange = { emailValue = it },
        icon = Icons.Default.MailOutline,
        placeholder = "Email address",
        keyboardType = KeyboardType.Email
    )
}

@Composable
private fun InputField(value: String, onValueChange: (String) -> Unit, icon: ImageVector, placeholder: String, keyboardType: KeyboardType) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            focusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface,
            leadingIconColor = MaterialTheme.colors.onSurface
        ),
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = "email", Modifier.size(24.dp))
        },
        placeholder = {
            Text(placeholder, style = MaterialTheme.typography.body1)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_bg),
            contentDescription = "bg",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "Welcome back", style = MaterialTheme.typography.h2, color = White, modifier = Modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(152.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLightLoginScreen() {
    WeTradeTheme(darkTheme = false) {
        LoginScreen()
    }
}

@Preview
@Composable
fun PreviewDarkLoginScreen() {
    WeTradeTheme(darkTheme = true) {
        LoginScreen()
    }
}
