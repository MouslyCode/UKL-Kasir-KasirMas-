package com.example.kasirmas

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirmas.databinding.ActivityDataMenuBinding

class DataMenu : AppCompatActivity() {

    private lateinit var binding : ActivityDataMenuBinding
    private lateinit var menuViewModel: MenuViewModel
    private var foodmass = ArrayList<Foodmas>()
    private var drinkmass = ArrayList<Drinkmas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        menuViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MenuViewModel::class.java]

        initFoodmasRv()
        initDrinkmasRv()

        binding.dataMenuIbBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataMenuFabAdd.setOnClickListener {
            startActivity(Intent(this, AddMenu::class.java))
            finish()
        }
    }

    private fun initFoodmasRv(){


        val Adapterfoodmas = AdapterFoodmas(this, foodmass, menuViewModel)

        val rvFood = binding.dataMenuRvMakanan
        rvFood.setHasFixedSize(true)
        rvFood.layoutManager = LinearLayoutManager(this)
        rvFood.adapter = Adapterfoodmas

        menuViewModel.getFoodmas.observe(this) { list ->
            list?.let {
                foodmass = it as ArrayList<Foodmas>
                Adapterfoodmas.updateFoodmas(foodmass)
            }
        }
    }

    private fun initDrinkmasRv(){

        val Adapterdrinkmas = AdapterDrinkmas(this, drinkmass, menuViewModel)

        val rvDrinkmas = binding.dataMenuRvMinuman
        rvDrinkmas.setHasFixedSize(true)
        rvDrinkmas.layoutManager = LinearLayoutManager(this)
        rvDrinkmas.adapter = Adapterdrinkmas

        menuViewModel.getDrinkmas.observe(this) { list ->
            list?.let {
                drinkmass = it as ArrayList<Drinkmas>
                Adapterdrinkmas.updateDrinkmas(drinkmass)
            }
        }
    }
}