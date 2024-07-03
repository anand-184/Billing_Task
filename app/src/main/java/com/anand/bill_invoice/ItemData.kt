package com.anand.bill_invoice

data class ItemData(

    var itemNo: String? = "",  var itemName: String? = "",      var itemqty:String? = ""
) {
    fun getQty():Int{
        return itemqty?.toInt() ?: 0
    }
    override fun toString(): String{
        return "$itemName"

    }
}

