package com.anand.bill_invoice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.anand.bill_invoice.R.id
import com.anand.bill_invoice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    var navController : NavController? = null
    var itemArray = ArrayList<ItemData>()
    var ItemAdapter= ItemAdapterClass(itemArray)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController= findNavController(id.host)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.bottomNav?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.items-> navController?.navigate(R.id.itemFragment)
                R.id.bill-> navController?.navigate(R.id.billFragment)
            }
            return@setOnItemSelectedListener true
        }
        navController?.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id) {
                R.id.itemFragment -> {
                    supportActionBar?.title = "Items"
                    binding?.bottomNav?.menu?.findItem(R.id.itemFragment)?.isChecked = true
                }
                R.id.billFragment->{
                    supportActionBar?.title="Bill"
                    binding?.bottomNav?.menu?.findItem(R.id.billFragment)?.isChecked = true
                }
            }
        }
    }
}