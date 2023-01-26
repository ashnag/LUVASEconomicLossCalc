package com.example.luvaseconomiclosscalc

import android.content.Intent
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.luvaseconomiclosscalc.databinding.ActivityIntroBinding


private lateinit var binding: ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imgHome.setOnClickListener {
            val homeIntent = Intent(this@IntroActivity, HomeActivity::class.java)
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(homeIntent)
        }

        binding.imgHome.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val view = v as ImageButton
                    //overlay is black with transparency of 0x77 (119)
                    view.drawable.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP)
                    view.invalidate()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val view = v as ImageButton
                    //clear the overlay
                    view.drawable.clearColorFilter()
                    view.invalidate()
                }
            }
            false
        }
        binding.tvClickHere.setOnClickListener {
            val homeIntent = Intent(this@IntroActivity, HomeActivity::class.java)
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(homeIntent)
        }

        binding.tvClickHere.paintFlags = binding.tvClickHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.tvClickHere.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val view = v as TextView
                    //overlay is black with transparency of 0x77 (119)
                    view.setBackgroundColor(0x77000000)
                    view.invalidate()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val view = v as TextView
                    //clear the overlay
                    view.setBackgroundColor(0xffffff)
                    view.invalidate()
                }
            }
            false
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        moveTaskToBack(true)
    }
}