package com.anand.bill_invoice

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.anand.bill_invoice.databinding.FragmentItemBinding


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ItemFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var binding:  FragmentItemBinding? = null
    var mainActivity: MainActivity? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mainActivity = activity as MainActivity
       mainActivity?.itemArray?.add(ItemData("1","Samosa","4"))
        mainActivity?.itemArray?.add(ItemData("2.","Cheese","5"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity?.binding?.itemListView?.adapter = mainActivity?.ItemAdapter
        binding?.fabAdditem?.setOnClickListener {
            var addDialog = Dialog(requireContext()).apply {
               setContentView(R.layout.add_dialog)
                window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
                show()
            }
            var btnAdded =addDialog.findViewById<Button>(R.id.btnAdd)
            var newItemNo = addDialog.findViewById<EditText>(R.id.etAdditemNo)
            var newItemName = addDialog.findViewById<EditText>(R.id.etAddItemName)
            var newItemQty = addDialog.findViewById<EditText>(R.id.etAdditemQty)
            btnAdded.setOnClickListener {
                if(newItemName.text.toString().isNullOrEmpty()){
                    newItemName.error ="Enter Item Name"
                }else if(newItemQty.text.toString().isNullOrEmpty()){
                    newItemQty.error = "Enter Quantity"
                }else if(newItemNo.text.toString().isNullOrEmpty()){
                    newItemNo.error = "Enter Item No."
                }else{
                   mainActivity?.itemArray?.add(ItemData("$newItemNo",
                        "$newItemName","$newItemQty"))
                    mainActivity?.ItemAdapter?.notifyDataSetChanged()
                    addDialog.dismiss()
                }
            }
        }
        mainActivity?.binding?.itemListView?.setOnItemClickListener { adapterView, view, i, l ->

             var updateDialog = Dialog(requireContext()).apply {
                 setContentView(R.layout.update_dialog_layout)
                 show()
             }
            var updateButton = updateDialog.findViewById<Button>(R.id.btnUpdate)
            var item_name = updateDialog.findViewById<TextView>(R.id.tvitemname)
            var item_qty = updateDialog.findViewById<EditText>(R.id.etitemqty)
            var selectedItem = mainActivity?.binding?.itemListView!!.selectedItem as String
            item_name.setText("$selectedItem")
            updateButton.setOnClickListener {
                if(item_qty.text.toString().trim().isNullOrEmpty()){
                    item_qty.error = "enter qty"
                }else{
                     mainActivity?.itemArray?.set(i,ItemData("$i","$item_name","$item_qty"))
                    updateDialog.dismiss()

                }

            }
        }
        mainActivity?.binding?.itemListView?.setOnItemLongClickListener { adapterView, view, i, l ->
            var alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Remove Item")
            alertDialog.setMessage("Do You want to Delete Item")
            alertDialog.setPositiveButton("YES"){_,_->
                mainActivity?.itemArray?.removeAt(i)
                mainActivity?.ItemAdapter?.notifyDataSetChanged()
            }
            alertDialog.setNegativeButton("NO"){_,_->
            }
            return@setOnItemLongClickListener true
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}