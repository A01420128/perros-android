package com.example.perros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perros.adapter.PerrosAdapter
import com.example.perros.databinding.ActivityMainBinding
import com.example.perros.patterns.RetrofitSingleton
import com.example.perros.response.PerroResponse
import com.example.perros.service.PerrosAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity(),
    android.widget.SearchView.OnQueryTextListener {
    private lateinit var adapter: PerrosAdapter
    private lateinit var binding: ActivityMainBinding
    private val perrosPics = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
    }
    private fun initAdapter(){
        adapter = PerrosAdapter(perrosPics)
        binding.perros.layoutManager = LinearLayoutManager(this)
        binding.perros.adapter = adapter
        binding.busqueda.setOnQueryTextListener(this)
        //buscarPerrosPorRaza("labrador")
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun buscarPerrosPorRaza(raza: String){
        CoroutineScope(Dispatchers.IO).launch {
            val llamado = RetrofitSingleton.getRetroFit().create(PerrosAPIService::class.java).getPerrosPorRaza("$raza/images")
            val perrosRespose : PerroResponse? = llamado.body()
            runOnUiThread {
                if (llamado.isSuccessful) {
                    val imagenes: List<String> = perrosRespose?.imagenes ?: emptyList()
                    perrosPics.clear()
                    perrosPics.addAll(imagenes)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                }
            }
            hideKeyboard()
        }


    }

    override fun onQueryTextSubmit(searchString: String?): Boolean {
        if(!searchString.isNullOrEmpty()){
            buscarPerrosPorRaza(searchString.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}
