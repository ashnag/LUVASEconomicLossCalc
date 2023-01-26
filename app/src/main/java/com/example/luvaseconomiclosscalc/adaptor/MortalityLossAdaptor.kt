package com.example.luvaseconomiclosscalc.adaptor

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luvaseconomiclosscalc.R
import com.example.luvaseconomiclosscalc.models.MortalityLoss

class MortalityLossAdaptor(val mortalityLossItems: List<MortalityLoss>, var onTextChanged: (String, Int) -> Unit) : RecyclerView.Adapter<MortalityLossAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MortalityLossAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mortality, parent, false)
        Log.i("Testing", "Binding")
        return ViewHolder(view)
    }

    override fun getItemCount() = mortalityLossItems.size

    override fun onBindViewHolder(holder: MortalityLossAdaptor.ViewHolder, position: Int) {
        val mortalityLossItem = mortalityLossItems[position]
        Log.i("Testing", "Binding")
        holder.bind(mortalityLossItem, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMortalityAgeGroup: TextView = itemView.findViewById(R.id.tvMortalityAgeGroup)
        private val etMortalityNumAnimals : EditText = itemView.findViewById(R.id.etMortalityNumAnimals)
        private val etCostMortality : EditText = itemView.findViewById(R.id.etCostMortality)
        private val tvTotalLossMortality : TextView = itemView.findViewById(R.id.tvTotalLossMortality)

        init {
            etMortalityNumAnimals.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })


            etCostMortality.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

        }

        fun bind(mortalityLoss: MortalityLoss, position: Int) {
            tvMortalityAgeGroup.text = mortalityLoss.ageGroup
            etMortalityNumAnimals.setText(mortalityLoss.numOfAnimal.toString())
            etCostMortality.setText(mortalityLoss.costPerAnimal.toString())

            tvTotalLossMortality.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    onTextChanged(p0.toString(), position)
                }
            })
        }

        private fun computeTotalLossPerAgeGroup() {
            if(etMortalityNumAnimals.text.isEmpty() || etCostMortality.text.isEmpty()) {
                tvTotalLossMortality.text = "0.0"
                return
            }

            val numOfAnimals  = etMortalityNumAnimals.text.toString().toFloat()
            val costMortality  = etCostMortality.text.toString().toFloat()
            val totalLossPerAgeGroup = numOfAnimals * costMortality

            tvTotalLossMortality.setText("%.2f".format(totalLossPerAgeGroup))

        }
    }
}
