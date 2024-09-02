package com.maya.batteryalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.maya.batteryalarm.ui.BatteryViewModel

class BatteryReciever (private val batteryVM: BatteryViewModel) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action == Intent.ACTION_BATTERY_CHANGED) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val levelPercentage = level / scale.toFloat()
            val batteryLevel = (levelPercentage * 100).toInt()
            batteryVM.updateChargedLevel(batteryLevel)
        }
    }
}