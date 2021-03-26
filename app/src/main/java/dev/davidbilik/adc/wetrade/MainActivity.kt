/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.davidbilik.adc.wetrade

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.davidbilik.adc.wetrade.theme.ui.WeTradeTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.makeTransparentStatusBar()

        setContent {
            val navController = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides navController
            ) {
                WeTradeTheme {
                    NavHost(navController, startDestination = "welcome") {
                        composable("welcome") { WelcomeScreen() }
                        composable("login") { LoginScreen() }
                        composable("home") { HomeScreen() }
                    }
                }
            }
        }
    }

    private fun Window.makeTransparentStatusBar() {
        markAttributes(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            true
        )
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        markAttributes(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            false
        )
        statusBarColor = Color.TRANSPARENT
    }

    private fun Window.markAttributes(bits: Int, value: Boolean) {
        val params = attributes
        if (value) {
            params.flags = params.flags or bits
        } else {
            params.flags = params.flags and bits.inv()
        }
        attributes = params
    }
}
