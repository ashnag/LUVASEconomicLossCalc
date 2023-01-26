package com.example.luvaseconomiclosscalc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luvaseconomiclosscalc.*
import com.example.luvaseconomiclosscalc.databinding.ActivityGoatBinding
import com.example.luvaseconomiclosscalc.models.AnimalTreatmentLoss
import com.example.luvaseconomiclosscalc.models.DraftCapabilityLoss
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss
import com.example.luvaseconomiclosscalc.models.MortalityLoss

class GoatActivity : AppCompatActivity(),FragmentLossChangeEventListener {

    private lateinit var netLossPerCateogry: ArrayList<Float>
    private lateinit var goatBinding: ActivityGoatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goatBinding = ActivityGoatBinding.inflate(layoutInflater)
        setContentView(goatBinding.root)

        netLossPerCateogry = ArrayList()

        // Add Milk Produce Reduction Loss details
        val milkProducefragment = MilkProduceReductionFragment.newInstance(0)
        val milkProduceLossItems = ArrayList<MilkProduceLoss>()
        milkProduceLossItems.add(MilkProduceLoss("Adult", 0, 0, 0.0f))
        milkProducefragment.setMilkProduceItems(milkProduceLossItems)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flGoatReductionMilkProd, milkProducefragment)
            commit()
        }

        // Add Mortality Loss details
        val mortalityLossFragment = MortalityFragment.newInstance(1)
        val  mortalityLossItems = ArrayList<MortalityLoss>()
        mortalityLossItems.add(MortalityLoss("Adult",0,0.0f))
        mortalityLossItems.add(MortalityLoss("Kid",0,0.0f))

        mortalityLossFragment.setMortalityItems(mortalityLossItems)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flGoatMortality, mortalityLossFragment)
            commit()
        }

        // Add Affected Animals Treatment Loss details
        val animalTreatmentLossFragment = AffectedAnimalTreatmentFragment.newInstance(2)
        val  animalLossItems = ArrayList<AnimalTreatmentLoss>()
        animalLossItems.add(AnimalTreatmentLoss("Adult",0,0.0f))
        animalLossItems.add(AnimalTreatmentLoss("Kid",0,0.0f))

        animalTreatmentLossFragment.setAnimalTreatmentItems(animalLossItems)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flGoatAnimalTreatment, animalTreatmentLossFragment)
            commit()
        }

    }


    private fun createHomeActivityWithResult() {
        val parentIntent = Intent(applicationContext, HomeActivity::class.java)
        parentIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        parentIntent.putExtra("totalLossGoat", goatBinding.tvGoatNetEconomicLoss.text.toString().toFloat())
        startActivity(parentIntent)
    }

    override fun onBackPressed() {
        createHomeActivityWithResult()
    }

    override fun onSupportNavigateUp(): Boolean {
        createHomeActivityWithResult()
        return true
    }

    override fun computeNetEconomicLoss(categoryLoss: String, index: Int?) {
        netLossPerCateogry[index!!] = categoryLoss.toFloat()

        var netLoss = 0.0f
        for (loss in netLossPerCateogry )
        {
            netLoss += loss
        }
        goatBinding.tvGoatNetEconomicLoss.text = netLoss.toString()
    }
}