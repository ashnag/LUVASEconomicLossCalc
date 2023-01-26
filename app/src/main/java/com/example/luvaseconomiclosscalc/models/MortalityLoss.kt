package com.example.luvaseconomiclosscalc.models

import java.io.Serializable

data class MortalityLoss(val ageGroup: String, val numOfAnimal: Int,
                           var costPerAnimal: Float) : Serializable