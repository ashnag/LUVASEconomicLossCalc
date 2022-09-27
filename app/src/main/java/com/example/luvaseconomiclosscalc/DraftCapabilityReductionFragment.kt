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
import com.example.luvaseconomiclosscalc.adaptor.DraftCapabilityLossAdaptor
import com.example.luvaseconomiclosscalc.models.DraftCapabilityLoss


private const val ARG_PARAM1 = "fragmentIndex"



private lateinit var adapter: DraftCapabilityLossAdaptor
private lateinit var draftCapabilityLoss: DraftCapabilityLoss
private lateinit var recyclerView: RecyclerView


class DraftCapabilityReductionFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_draft_capability_reduction, container, false)
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

        recyclerView = view.findViewById(R.id.rvDraftCapabilityReduction)
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)
        val tvTotalLoss: TextView = view.findViewById(R.id.tvTotalDraftCapabilityLoss)
        adapter = DraftCapabilityLossAdaptor(draftCapabilityLoss) { totalLossText: String ->
            tvTotalLoss.text = totalLossText.toString()
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


    fun setDraftCapailityLoss(item: DraftCapabilityLoss) {
        draftCapabilityLoss = item
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
            DraftCapabilityReductionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}