package com.myapp.servicesample.ui.screen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.myapp.servicesample.ui.util.BindService

/**
 * Bind Service発火サンプル
 *
 * @param navController
 */
@Composable
fun BindScreen() {
    val context = LocalContext.current

    // Bind Service
    var mService: BindService? = null
    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as BindService.LocalBinder
            mService = binder.getService()
        }
        override fun onServiceDisconnected(className: ComponentName) {
            Log.e("TAG", "onServiceDisconnected")
        }
    }
    val serviceIntent = Intent(context, BindService::class.java)
    context.bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE)

    // ライフサイクル
    val observer = remember {
        LifecycleEventObserver {_, event ->
            when(event) {
                Lifecycle.Event.ON_CREATE -> {}
                Lifecycle.Event.ON_START -> {}
                Lifecycle.Event.ON_RESUME -> {}
                Lifecycle.Event.ON_PAUSE -> {}
                Lifecycle.Event.ON_STOP -> {}
                Lifecycle.Event.ON_DESTROY -> {
                    context.unbindService(mConnection)
                }
                Lifecycle.Event.ON_ANY -> {}
            }
        }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(Unit) {
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }


    // レイアウト
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bind Serviceを開始します。画面を破棄すると解除されます。")
        Button(onClick = {mService!!.startTenCount() }) {
            Text(text = "start bind Service")
        }
    }
}