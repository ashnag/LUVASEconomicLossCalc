package com.example.luvaseconomiclosscalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luvaseconomiclosscalc.databinding.ActivityMainBinding
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MilkProduceReductionFragment()
        val milkProduceLossItems = ArrayList<MilkProduceLoss>()
        milkProduceLossItems.add(MilkProduceLoss("Adult", 0, 0, 0.0f))
        fragment.setMilkProduceItems(milkProduceLossItems)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flReductionMilkProd, fragment)
            commit()
        }

        val fragment2 = MilkProduceReductionFragment()
        val milkProduceLossItems2 = ArrayList<MilkProduceLoss>()
        milkProduceLossItems2.add(MilkProduceLoss("Adult", 0, 0, 0.0f))
        milkProduceLossItems2.add(MilkProduceLoss("Heifer", 0, 0, 0.0f))
        milkProduceLossItems2.add(MilkProduceLoss("Calves", 0, 0, 0.0f))
        fragment2.setMilkProduceItems(milkProduceLossItems2)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flReductionMilkProd2, fragment2)
            commit()
        }

    }
}