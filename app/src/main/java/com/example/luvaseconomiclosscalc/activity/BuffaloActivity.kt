package com.example.luvaseconomiclosscalc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luvaseconomiclosscalc.FragmentLossChangeEventListener
import com.example.luvaseconomiclosscalc.MilkProduceReductionFragment
import com.example.luvaseconomiclosscalc.R
import com.example.luvaseconomiclosscalc.databinding.ActivityBuffaloBinding
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss


private lateinit var netLossPerCateogry: ArrayList<Float>
private lateinit var buffaloBinding: ActivityBuffaloBinding

class BuffaloActivity : AppCompatActivity(),FragmentLossChangeEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buffaloBinding = ActivityBuffaloBinding.inflate(layoutInflater)
        setContentView(buffaloBinding.root)

        netLossPerCateogry = ArrayList<Float>()

        val fragment = MilkProduceReductionFragment.newInstance(0)
        val milkProduceLossItems = ArrayList<MilkProduceLoss>()
        milkProduceLossItems.add(MilkProduceLoss("Adult", 0, 0, 0.0f))
        fragment.setMilkProduceItems(milkProduceLossItems)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flReductionMilkProd, fragment)
            commit()
        }

        val fragment2 = MilkProduceReductionFragment.newInstance(1)
        val milkProduceLossItems2 = ArrayList<MilkProduceLoss>()
        milkProduceLossItems2.add(MilkProduceLoss("Adult", 0, 0, 0.0f))
        milkProduceLossItems2.add(MilkProduceLoss("Heifer", 0, 0, 0.0f))
        milkProduceLossItems2.add(MilkProduceLoss("Calves", 0, 0, 0.0f))
        fragment2.setMilkProduceItems(milkProduceLossItems2)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flReductionMilkProd2, fragment2)
            commit()
        }

    }

    override fun computeNetEconomicLoss(categoryLoss: String, index: Int?) {
        netLossPerCateogry[index!!] = categoryLoss.toFloat()

        var netLoss : Float = 0.0f
        for (loss in netLossPerCateogry )
        {
            netLoss += loss
        }
        buffaloBinding.tvNetEconomicLoss.text = netLoss.toString()
    }
}