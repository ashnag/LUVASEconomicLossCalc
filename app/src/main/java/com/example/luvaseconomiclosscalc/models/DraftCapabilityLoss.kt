package com.example.luvaseconomiclosscalc.models

import java.io.Serializable

data class DraftCapabilityLoss( val numOfAnimal: Int, val durLoss: Int,
var costPerWorkingHour: Float) : Serializable