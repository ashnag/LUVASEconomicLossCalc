package com.example.luvaseconomiclosscalc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luvaseconomiclosscalc.*
import com.example.luvaseconomiclosscalc.databinding.ActivityPigBinding
import com.example.luvaseconomiclosscalc.models.AnimalTreatmentLoss
import com.example.luvaseconomiclosscalc.models.DraftCapabilityLoss
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss
import com.example.luvaseconomiclosscalc.models.MortalityLoss

class PigActivity : AppCompatActivity(),FragmentLossChangeEventListener {

    private lateinit var netLossPerCateogry: ArrayList<Float>
    private lateinit var pigBinding: ActivityPigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pigBinding = ActivityPigBinding.inflate(layoutInflater)
        setContentView(pigBinding.root)

        netLossPerCateogry = ArrayList()

        // Add Mortality Loss details
        val mortalityLossFragment = MortalityFragment.newInstance(0)
        val  mortalityLossItems = ArrayList<MortalityLoss>()
        mortalityLossItems.add(MortalityLoss("Adult",0,0.0f))
        mortalityLossItems.add(MortalityLoss("Piglet",0,0.0f))

        mortalityLossFragment.setMortalityItems(mortalityLossItems)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flPigMortality, mortalityLossFragment)
            commit()
        }

        // Add Affected Animals Treatment Loss details
        val animalTreatmentLossFragment = AffectedAnimalTreatmentFragment.newInstance(1)
        val  animalLossItems = ArrayList<AnimalTreatmentLoss>()
        animalLossItems.add(AnimalTreatmentLoss("Adult",0,0f))
        animalLossItems.add(AnimalTreatmentLoss("Piglet",0,0f))

        animalTreatmentLossFragment.setAnimalTreatmentItems(animalLossItems)
        netLossPerCateogry.add(0.0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flPigAnimalTreatment, animalTreatmentLossFragment)
            commit()
        }

    }

    private fun createHomeActivityWithResult() {
        val parentIntent = Intent(applicationContext, HomeActivity::class.java)
        parentIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        parentIntent.putExtra("totalLossPig", pigBinding.tvPigNetEconomicLoss.text.toString().toFloat())
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
        pigBinding.tvPigNetEconomicLoss.text = netLoss.toString()
    }
}