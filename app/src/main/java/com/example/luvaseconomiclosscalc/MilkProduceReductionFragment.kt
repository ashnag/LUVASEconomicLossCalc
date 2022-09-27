package com.example.luvaseconomiclosscalc

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luvaseconomiclosscalc.adaptor.MilkProduceLossAdaptor
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss

private const val ARG_PARAM1 = "fragmentIndex"



private lateinit var adapter: MilkProduceLossAdaptor
private lateinit var milkProduceLossItems: List<MilkProduceLoss>
private lateinit var recyclerView: RecyclerView
private lateinit var totalLossPerAgeGroup: ArrayList<Float>
//private lateinit var milkProduceLossbinding: FragmentMilkProduceReductionBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MilkProduceReductionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MilkProduceReductionFragment : Fragment() {

    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_milk_produce_reduction, container, false)
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

        recyclerView = view.findViewById(R.id.rvMilkProduceReduction)
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)
        val tvTotalLoss: TextView = view.findViewById(R.id.tvTotalLoss)
        adapter = MilkProduceLossAdaptor(milkProduceLossItems) { totalLossTextPerAgeGroup: String, position:Int ->
            totalLossPerAgeGroup.set(position, totalLossTextPerAgeGroup.toFloat())
            var totalLoss = 0.0f
            for (loss in totalLossPerAgeGroup )
            {
                totalLoss += loss
            }
            tvTotalLoss.text = totalLoss.toString()
        }
        recyclerView.adapter = adapter

        tvTotalLoss.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                listener?.computeNetEconomicLoss(p0.toString(), fragmentIndex)
            }

        })
        // 6. Bind the adapter to the data source to populate the RecyclerView
    }

     fun setMilkProduceItems(items: List<MilkProduceLoss>) {
        milkProduceLossItems = items
        totalLossPerAgeGroup = ArrayList<Float>(items.size)
        for (i in 1..items.size)  {
            totalLossPerAgeGroup.add(0.0f)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            MilkProduceReductionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}
