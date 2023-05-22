package com.example.kasirmas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kasirmas.databinding.ActivityUpdateMenuBinding

class UpdateMenu : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateMenuBinding
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.updateMenuIbBack.setOnClickListener {
            startActivity(Intent(this, DataMenu::class.java ))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        val variety = intent.getStringExtra("variety")
        val menuName = binding.updateMenuEtName
        val description = binding.updateMenuEtDesc
        val harga = binding.updateMenuEtHarga


        menuName.setText(intent.getStringExtra("menuName"))
        description.setText(intent.getStringExtra("desc"))
        harga.setText(intent.getStringExtra("price"))

        menuViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MenuViewModel::class.java]

        binding.updateMenuBtnSave.setOnClickListener {
            val newMenuName = menuName.text.toString()
            val newDescription = description.text.toString()
            val newHarga = harga.text.toString()

            when{

                newMenuName.isEmpty() -> alert()
                newDescription.isEmpty() -> alert()
                newHarga.isEmpty() -> alert()

                else -> {
                    val price = harga.text.toString().toInt()

                    if (variety == "Makanan"){

                        val updatedFoodmas = Foodmas(name = newMenuName, description = newDescription, price = price, image = 0)
                        updatedFoodmas.id = id
                        menuViewModel.updateFoodmas(updatedFoodmas)
                        startActivity(Intent(this, DataMenu::class.java))
                        finish()
                    }

                    else {

                        val updatedDrinkmas = Drinkmas(name = newMenuName, description = newDescription, price = price, image = 0)
                        updatedDrinkmas.id = id
                        menuViewModel.updateDrinkmas(updatedDrinkmas)
                        startActivity(Intent(this, DataMenu::class.java))
                        finish()
                    }
                }
            }
        }
    }
    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}