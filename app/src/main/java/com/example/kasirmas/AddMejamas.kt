package com.example.kasirmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirmas.databinding.ActivityAddMejamasBinding

class AddMejamas : AppCompatActivity() {
    private lateinit var binding : ActivityAddMejamasBinding
    private lateinit var mejamasViewModel: MejamasViewModel
    private var variety = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMejamasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        mejamasViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MejamasViewModel::class.java]

        binding.addTableIbBack.setOnClickListener {
            startActivity(Intent(this, datamejamas::class.java ))
            finish()
        }


        binding.addTableBtnSave.setOnClickListener{
            val noMeja = binding.addTableEtName.text.toString()

            when{
                noMeja.isEmpty() -> alert()
                else -> {
                    mejamasViewModel.insertTable(Mejamas(number = noMeja.toInt()))
                    startActivity(Intent(this, datamejamas::class.java))
                    finish()
                }
            }
        }
    }
    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}
