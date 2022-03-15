package com.myapp.servicesample.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.myapp.servicesample.ui.util.ForegroundService

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Service Sample")
        Button(
            onClick = {
                val serviceIntent = Intent(context, ForegroundService::class.java)
                context.startService(serviceIntent)
            }
        ) {
            Text(text = "start Foreground Service")
        }
    }
}