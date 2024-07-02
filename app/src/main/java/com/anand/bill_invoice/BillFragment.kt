package com.anand.bill_invoice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.anand.bill_invoice.databinding.FragmentBillBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BillFragment : Fragment() {
    var mainActivity: MainActivity? = null
    var binding: FragmentBillBinding? = null
    lateinit var billAdapter: ArrayAdapter<ItemData>
    var qty = ""

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
        billAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mainActivity?.itemArray ?: arrayListOf()
        )
        binding?.dynamicSpinner?.adapter = billAdapter
        billAdapter.notifyDataSetChanged()
        binding?.tvselectedItemQty?.setText("1")
        binding?.btnMinusQty?.setOnClickListener {
            if (binding?.tvselectedItemQty?.text.toString().toInt() <= 1) {
                Toast.makeText(
                    requireContext(),
                    "ItemQty can't be decreased more",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var qty = binding?.tvselectedItemQty?.text.toString().toInt()
                qty--
                binding?.tvselectedItemQty?.setText(qty.toString())
            }
        }
        binding?.btnAddQty?.setOnClickListener {
            var selectedItem = binding?.dynamicSpinner?.selectedItem as ItemData
            if (binding?.tvselectedItemQty?.text.toString().toInt()>=(selectedItem.getQty()).toInt()) {
                Toast.makeText(requireContext(), "Qty can't be increased", Toast.LENGTH_SHORT).show()
            } else {
                var qty = binding?.tvselectedItemQty?.text.toString().toInt()
                qty++
                binding?.tvselectedItemQty?.setText(qty.toString())
            }

        }
        binding?.btnOrder?.setOnClickListener {
            var selectedItem = binding?.dynamicSpinner?.selectedItem as ItemData
            if (binding?.tvselectedItemQty?.text.toString().toInt() < 1) {
                Toast.makeText(requireContext(), "Order cannot be placed", Toast.LENGTH_LONG).show()
            } else if (binding?.tvselectedItemQty?.text.toString().toInt() > selectedItem.getQty()
            ) {
                Toast.makeText(requireContext(), "order cannot be placed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Order Placed", Toast.LENGTH_LONG).show()
            }

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


