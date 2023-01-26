package com.example.luvaseconomiclosscalc.models

import java.io.Serializable

data class AnimalTreatmentLoss(val ageGroup: String, var numOfAnimal: Int,
                               var costPerAnimal: Float) : Serializable