package com.maya.batteryalarm

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maya.batteryalarm.ui.BatteryViewModel
import com.maya.batteryalarm.ui.theme.BatteryAlarmTheme

class MainActivity : ComponentActivity() {
    private lateinit var batteryReceiver: BatteryReciever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val batteryVM = BatteryViewModel()
        batteryReceiver = getReceiver(this, batteryVM)

        enableEdgeToEdge()
        setContent {
            BatteryAlarmTheme {
                BatteryAlarm(viewModel = batteryVM)
            }
        }
    }
}


@Composable
fun BatteryAlarm(viewModel: BatteryViewModel = viewModel(), modifier: Modifier = Modifier) {
    val batteryLevel by viewModel.level.collectAsState()
    val batteryImage = if(batteryLevel>25) R.drawable.battery_full else R.drawable.battery_low

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(painter = painterResource(batteryImage),
            modifier = modifier
                .size(250.dp),
            contentDescription = "Battery image")
    }
}


@Preview(showSystemUi = true)
@Composable
private fun BatteryAlarmPreview(){
    BatteryAlarm()
}


fun getReceiver(activity: ComponentActivity, batteryVM: BatteryViewModel): BatteryReciever {
    val batteryReceiver = BatteryReciever(batteryVM)
    val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    activity.registerReceiver(batteryReceiver, filter)
    return batteryReceiver
}
