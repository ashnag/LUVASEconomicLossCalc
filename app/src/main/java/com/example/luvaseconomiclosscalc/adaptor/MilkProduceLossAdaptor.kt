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
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss

class MilkProduceLossAdaptor(val milkProduceLossItems: List<MilkProduceLoss>, var onTextChanged: (String, Int) -> Unit) : RecyclerView.Adapter<MilkProduceLossAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_milk_reduction, parent, false)
        Log.i("Testing", "Binding")
        return ViewHolder(view)
    }

    override fun getItemCount() = milkProduceLossItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val milkProduceLossItem = milkProduceLossItems[position]
        Log.i("Testing", "Binding")
        holder.bind(milkProduceLossItem, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val etNumAnimals : EditText = itemView.findViewById(R.id.etNumAnimals)
        private val etDurLoss : EditText = itemView.findViewById(R.id.etDurLoss)
        private val etCostMilk : EditText = itemView.findViewById(R.id.etCostMilk)
        private val etMilkYieldReduction : EditText = itemView.findViewById(R.id.etMilkYieldReduction)
        private val tvTotalLossMilkProduction : TextView = itemView.findViewById(R.id.tvTotalLossMilkProduction)

        init {
            etNumAnimals.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

            etDurLoss.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

            etCostMilk.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

            etMilkYieldReduction.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

        }

        fun bind(milkProduceLoss: MilkProduceLoss, position: Int) {

            tvTotalLossMilkProduction.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    onTextChanged(p0.toString(), position)
                }
            })
        }

        private fun computeTotalLossPerAgeGroup() {
            if(etNumAnimals.text.isEmpty() || etCostMilk.text.isEmpty()
                || etDurLoss.text.isEmpty() || etMilkYieldReduction.text.isEmpty()) {
                tvTotalLossMilkProduction.text = "0.0"
                return
            }

            val numOfAnimals  = etNumAnimals.text.toString().toFloat()
            val costMilk  = etCostMilk.text.toString().toFloat()
            val milkYieldReduction  = etMilkYieldReduction.text.toString().toFloat()
            val durLoss  = etDurLoss.text.toString().toFloat()
            val totalLossPerAgeGroup = numOfAnimals * costMilk * milkYieldReduction * durLoss

            tvTotalLossMilkProduction.setText("%.2f".format(totalLossPerAgeGroup))

        }
    }
}
