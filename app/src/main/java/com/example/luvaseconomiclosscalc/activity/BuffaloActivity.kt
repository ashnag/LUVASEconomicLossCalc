package com.example.luvaseconomiclosscalc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.luvaseconomiclosscalc.*
import com.example.luvaseconomiclosscalc.databinding.ActivityBuffaloBinding
import com.example.luvaseconomiclosscalc.models.AnimalTreatmentLoss
import com.example.luvaseconomiclosscalc.models.DraftCapabilityLoss
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss
import com.example.luvaseconomiclosscalc.models.MortalityLoss


class BuffaloActivity : AppCompatActivity(),FragmentLossChangeEventListener {

    private lateinit var netLossPerCateogry: ArrayList<Float>
    private lateinit var buffaloBinding: ActivityBuffaloBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buffaloBinding = ActivityBuffaloBinding.inflate(layoutInflater)
        setContentView(buffaloBinding.root)

        netLossPerCateogry = ArrayList()

        // Add Milk Produce Reduction Loss details
        val milkProducefragment = MilkProduceReductionFragment.newInstance(0)
        val milkProduceLossItems = ArrayList<MilkProduceLoss>()
        milkProduceLossItems.add(MilkProduceLoss("Adult", 0, 0, 0f))
        milkProducefragment.setMilkProduceItems(milkProduceLossItems)
        netLossPerCateogry.add(0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flReductionMilkProd, milkProducefragment)
            commit()
        }

        // Add Draft Capability Loss details
        val draftCapabilityFragment = DraftCapabilityReductionFragment.newInstance(1)
        draftCapabilityFragment.setDraftCapailityLoss(DraftCapabilityLoss(0,0,0f))
        netLossPerCateogry.add(0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flReductionDraftCapability, draftCapabilityFragment)
            commit()
        }

        // Add Mortality Loss details
        val mortalityLossFragment = MortalityFragment.newInstance(2)
        val  mortalityLossItems = ArrayList<MortalityLoss>()
        mortalityLossItems.add(MortalityLoss("Adult",0,0f))
        mortalityLossItems.add(MortalityLoss("Heifer",0,0f))
        mortalityLossItems.add(MortalityLoss("Calf",0,0f))

        mortalityLossFragment.setMortalityItems(mortalityLossItems)
        netLossPerCateogry.add(0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMortality, mortalityLossFragment)
            commit()
        }

        // Add Affected Animals Treatment Loss details
        val animalTreatmentLossFragment = AffectedAnimalTreatmentFragment.newInstance(3)
        val  animalLossItems = ArrayList<AnimalTreatmentLoss>()
        animalLossItems.add(AnimalTreatmentLoss("Adult",0,0f))
        animalLossItems.add(AnimalTreatmentLoss("Heifer",0,0f))
        animalLossItems.add(AnimalTreatmentLoss("Calf",0,0f))

        animalTreatmentLossFragment.setAnimalTreatmentItems(animalLossItems)
        netLossPerCateogry.add(0f)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flAnimalTreatment, animalTreatmentLossFragment)
            commit()
        }

    }

    private fun createHomeActivityWithResult() {
        val parentIntent = Intent(applicationContext, HomeActivity::class.java)
        parentIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        parentIntent.putExtra("totalLossBuffalo", buffaloBinding.tvNetEconomicLoss.text.toString().toFloat())
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
        buffaloBinding.tvNetEconomicLoss.text = netLoss.toString()
    }
}