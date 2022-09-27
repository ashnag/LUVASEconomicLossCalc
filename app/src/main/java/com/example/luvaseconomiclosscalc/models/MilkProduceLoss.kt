package com.example.luvaseconomiclosscalc.models

import java.io.Serializable

data class MilkProduceLoss(val ageGroup: String, val numOfAnimal: Int, val durLoss: Int,
                           var costPerLitre: Float) : Serializable
