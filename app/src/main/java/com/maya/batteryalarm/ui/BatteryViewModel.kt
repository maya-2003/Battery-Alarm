package com.maya.batteryalarm.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BatteryViewModel : ViewModel(){
    private val _level = MutableStateFlow(0)
    val level= _level.asStateFlow()

    fun updateChargedLevel(level: Int) {
        _level.value = level
    }
}