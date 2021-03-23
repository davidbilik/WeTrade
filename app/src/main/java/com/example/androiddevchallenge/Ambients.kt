package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavController = staticCompositionLocalOf<NavController> { error("You must specify a NavController.") }

@Composable
fun findNavController() = LocalNavController.current
