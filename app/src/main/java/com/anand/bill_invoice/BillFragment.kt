package com.anand.bill_invoice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.anand.bill_invoice.databinding.FragmentBillBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class BillFragment : Fragment() {
    var mainActivity: MainActivity? = null
    var binding:FragmentBillBinding? = null
    var selectedItemArray = arrayListOf<ItemData>()
   lateinit  var billAdapter: ArrayAdapter<ItemData>

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBillBinding.inflate(layoutInflater)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,selectedItemArray)
        binding?.dynamicSpinner?.adapter = billAdapter
        billAdapter.notifyDataSetChanged()





        binding?.btnOrder?.setOnClickListener {

        }



    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BillFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}