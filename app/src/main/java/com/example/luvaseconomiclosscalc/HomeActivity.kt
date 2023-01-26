package com.example.luvaseconomiclosscalc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.luvaseconomiclosscalc.activity.*
import com.example.luvaseconomiclosscalc.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding
private var netLossCattle: Float = 0.0f
private var netLossBuffalo: Float = 0.0f
private var netLossPig: Float = 0.0f
private var netLossSheep: Float = 0.0f
private var netLossGoat: Float = 0.0f



class HomeActivity : AppCompatActivity() {

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imBuffalo.setOnClickListener {
            val buffaloIntent = Intent(this@HomeActivity, BuffaloActivity::class.java)
            buffaloIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(buffaloIntent)
        }

        binding.imCattle.setOnClickListener {
            val cattleIntent = Intent(this@HomeActivity, CattleActivity::class.java)
            cattleIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            resultLauncher.launch(cattleIntent)
        }

        binding.imPig.setOnClickListener {
            val pigIntent = Intent(this@HomeActivity, PigActivity::class.java)
            pigIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(pigIntent)
        }

        binding.imSheep.setOnClickListener {
            val sheepIntent = Intent(this@HomeActivity, SheepActivity::class.java)
            sheepIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(sheepIntent)
        }

        binding.imGoat.setOnClickListener {
            val goatIntent = Intent(this@HomeActivity, GoatActivity::class.java)
            goatIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(goatIntent)
        }

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        moveTaskToBack(true)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent!!.hasExtra("totalLossCattle")) {
            netLossCattle =  intent.getFloatExtra("totalLossCattle", 0.0f)
        }
        if(intent!!.hasExtra("totalLossBuffalo")) {
            netLossBuffalo =  intent.getFloatExtra("totalLossBuffalo", 0.0f)
        }
        if(intent!!.hasExtra("totalLossPig")) {
            netLossPig =  intent.getFloatExtra("totalLossPig", 0.0f)
        }
        if(intent!!.hasExtra("totalLossSheep")) {
            netLossSheep =  intent.getFloatExtra("totalLossSheep", 0.0f)
        }
        if(intent!!.hasExtra("totalLossGoat")) {
            netLossGoat =  intent.getFloatExtra("totalLossGoat", 0.0f)
        }
        var grandTotal = netLossCattle + netLossBuffalo + netLossPig + netLossSheep + netLossGoat
        binding.tvGrandTotal.text = grandTotal.toString()

    }

}