package com.anand.bill_invoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemAdapterClass(var itemArray: ArrayList<ItemData>):BaseAdapter() {
    override fun getCount(): Int {
       return itemArray.size
    }
    override fun getItem(p0: Int): Any {
       return itemArray[p0]
    }
    override fun getItemId(p0: Int): Long {
        return 1L
    }
    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).
        inflate(R.layout.item_lv_layout,parent,false)
        var tvItemNo = view.findViewById<TextView>(R.id.tvItemNo)
        var tvItemName = view.findViewById<TextView>(R.id.tvItemName)
        var tvItemQty = view.findViewById<TextView>(R.id.tvItemQty)
        tvItemNo.setText(itemArray[p0].itemNo.toString())
        tvItemName.setText(itemArray[p0].itemName)
        tvItemQty.setText(itemArray[p0].itemqty.toString())
        return view
    }
}