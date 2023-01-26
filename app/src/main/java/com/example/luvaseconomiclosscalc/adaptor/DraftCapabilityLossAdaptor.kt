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
import com.example.luvaseconomiclosscalc.models.DraftCapabilityLoss

class DraftCapabilityLossAdaptor(val draftCapabilityLoss: DraftCapabilityLoss, var onTextChanged: (String) -> Unit) : RecyclerView.Adapter<DraftCapabilityLossAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DraftCapabilityLossAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_draft_reduction, parent, false)
        Log.i("Testing", "Binding")
        return ViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: DraftCapabilityLossAdaptor.ViewHolder, position: Int) {
        Log.i("Testing", "Binding")
        holder.bind(draftCapabilityLoss, position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val etNumAnimals: EditText = itemView.findViewById(R.id.etDraftNumAnimals)
        private val etDraftDurLoss: EditText = itemView.findViewById(R.id.etDraftDurLoss)
        private val etCostWorkHour: EditText = itemView.findViewById(R.id.etCostWorkingHour)
        private val etWorkingHourReduction: EditText =
            itemView.findViewById(R.id.etWorkingHourReduce)
        private val tvTotalDraftCapabilityReduce: TextView =
            itemView.findViewById(R.id.tvTotalDraftCapabilityReduce)

        init {
            etNumAnimals.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

            etDraftDurLoss.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

            etCostWorkHour.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

            etWorkingHourReduction.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    computeTotalLossPerAgeGroup()
                }
            })

        }

        fun bind(draftCapabilityLoss: DraftCapabilityLoss, position: Int) {
            etNumAnimals.setText(draftCapabilityLoss.numOfAnimal.toString())
            etDraftDurLoss.setText(draftCapabilityLoss.durLoss.toString())
            etCostWorkHour.setText(draftCapabilityLoss.costPerWorkingHour.toString())
            tvTotalDraftCapabilityReduce.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    onTextChanged(p0.toString())
                }
            })
        }

        private fun computeTotalLossPerAgeGroup() {
            if (etNumAnimals.text.isEmpty() || etCostWorkHour.text.isEmpty()
                || etDraftDurLoss.text.isEmpty() || etWorkingHourReduction.text.isEmpty()
            ) {
                tvTotalDraftCapabilityReduce.text = "0.0"
                return
            }

            val numOfAnimals = etNumAnimals.text.toString().toFloat()
            val costMilk = etCostWorkHour.text.toString().toFloat()
            val milkYieldReduction = etWorkingHourReduction.text.toString().toFloat()
            val durLoss = etDraftDurLoss.text.toString().toFloat()
            val totalLossPerAgeGroup = numOfAnimals * costMilk * milkYieldReduction * durLoss

            tvTotalDraftCapabilityReduce.setText("%.2f".format(totalLossPerAgeGroup))

        }
    }

}
