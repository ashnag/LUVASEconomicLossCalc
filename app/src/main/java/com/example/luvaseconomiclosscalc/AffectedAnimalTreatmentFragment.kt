package com.example.luvaseconomiclosscalc

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luvaseconomiclosscalc.adaptor.AnimalTreatmentLossAdaptor
import com.example.luvaseconomiclosscalc.models.AnimalTreatmentLoss

private const val ARG_PARAM1 = "fragmentIndex"



private lateinit var adapter: AnimalTreatmentLossAdaptor
private lateinit var animalTreatmentLossItems: List<AnimalTreatmentLoss>
private lateinit var recyclerView: RecyclerView
private lateinit var totalLossPerAgeGroup: ArrayList<Float>


/**
 * A simple [Fragment] subclass.
 * Use the [AffectedAnimalTreatmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AffectedAnimalTreatmentFragment : Fragment() {
    private var fragmentIndex: Int? = null
    private var listener: FragmentLossChangeEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentIndex = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_affected_animal_treatment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentLossChangeEventListener
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvTreatment)
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)
        val tvTotalLoss: TextView = view.findViewById(R.id.tvTreatmentTotalLoss)
        adapter = AnimalTreatmentLossAdaptor(animalTreatmentLossItems) { totalLossTextPerAgeGroup: String, position:Int ->
            totalLossPerAgeGroup.set(position, totalLossTextPerAgeGroup.toFloat())
            var totalLoss = 0.0f
            for (loss in totalLossPerAgeGroup )
            {
                totalLoss += loss
            }
            tvTotalLoss.text = totalLoss.toString()
        }
        recyclerView.adapter = adapter

        tvTotalLoss.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                listener?.computeNetEconomicLoss(p0.toString(), fragmentIndex)
            }

        })
        // 6. Bind the adapter to the data source to populate the RecyclerView
    }



    fun setAnimalTreatmentItems(items: List<AnimalTreatmentLoss>) {
        animalTreatmentLossItems = items
        totalLossPerAgeGroup = ArrayList<Float>(items.size)
        for (i in 1..items.size)  {
            totalLossPerAgeGroup.add(0.0f)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Int) =
            AffectedAnimalTreatmentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}