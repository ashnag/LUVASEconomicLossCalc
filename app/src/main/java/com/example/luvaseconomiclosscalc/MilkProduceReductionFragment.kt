package com.example.luvaseconomiclosscalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luvaseconomiclosscalc.models.MilkProduceLoss
import java.io.Serializable

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



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
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_milk_produce_reduction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvMilkProduceReduction)
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)
        val tvTotalLoss: TextView = view.findViewById(R.id.tvTotalLoss)
        adapter = MilkProduceLossAdaptor(milkProduceLossItems) { totalLossTextPerAgeGroup: String, position:Int ->
            totalLossPerAgeGroup.set(position, totalLossTextPerAgeGroup.toFloat())
            var totalLoss : Float = 0.0f
            for (loss in totalLossPerAgeGroup )
            {
                totalLoss += loss
            }
            tvTotalLoss.text = totalLoss.toString()
        }
        recyclerView.adapter = adapter
        // 6. Bind the adapter to the data source to populate the RecyclerView
    }

    public fun setMilkProduceItems(items: List<MilkProduceLoss>) {
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
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
