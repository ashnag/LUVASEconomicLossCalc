package com.example.luvaseconomiclosscalc.adaptor

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luvaseconomiclosscalc.R
import com.example.luvaseconomiclosscalc.models.AnimalTreatmentLoss

class AnimalTreatmentLossAdaptor(val animalTreatmentLossItems: List<AnimalTreatmentLoss>, var onTextChanged: (String, Int) -> Unit) : RecyclerView.Adapter<AnimalTreatmentLossAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalTreatmentLossAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_affected_animal_treatment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = animalTreatmentLossItems.size

    override fun onBindViewHolder(holder: AnimalTreatmentLossAdaptor.ViewHolder, position: Int) {
        val animalTreatmentLossItem = animalTreatmentLossItems[position]
        holder.bind(animalTreatmentLossItem, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTreatmentAgeGroup: TextView = itemView.findViewById(R.id.tvTreatmentAgeGroup)
        private val etTreatmentNumAnimals : EditText = itemView.findViewById(R.id.etTreatmentNumAnimals)
        private val etCostTreatment : EditText = itemView.findViewById(R.id.etCostTreatment)
        private val tvTotalLossTreatment : TextView = itemView.findViewById(R.id.tvTotalLossTreatment)

        init {
            etTreatmentNumAnimals.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })


            etCostTreatment.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

        }

        fun bind(treatmentLoss: AnimalTreatmentLoss, position: Int) {
            tvTreatmentAgeGroup.text = treatmentLoss.ageGroup
            etTreatmentNumAnimals.setText(treatmentLoss.numOfAnimal.toString())
            etCostTreatment.setText(treatmentLoss.costPerAnimal.toString())

            tvTotalLossTreatment.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    onTextChanged(p0.toString(), position)
                }
            })
        }

        private fun computeTotalLossPerAgeGroup() {
            if(etTreatmentNumAnimals.text.isEmpty() || etCostTreatment.text.isEmpty()) {
                tvTotalLossTreatment.text = "0.0"
                return
            }

            val numOfAnimals  = etTreatmentNumAnimals.text.toString().toFloat()
            val costTreatment  = etCostTreatment.text.toString().toFloat()
            val totalLossPerAgeGroup = numOfAnimals * costTreatment

            tvTotalLossTreatment.setText("%.2f".format(totalLossPerAgeGroup))

        }
    }
}
