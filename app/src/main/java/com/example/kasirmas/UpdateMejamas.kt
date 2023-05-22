package com.example.kasirmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirmas.databinding.ActivityUpdateMejamasBinding

class UpdateMejamas : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateMejamasBinding
    private lateinit var mejamasViewModel: MejamasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMejamasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.updateTableIbBack.setOnClickListener {
            startActivity(Intent(this, datamejamas::class.java ))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        val noMeja = binding.updateTableEtNo


        noMeja.setText(intent.getStringExtra("noMeja"))

        mejamasViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MejamasViewModel::class.java]

        binding.updateTableBtnSave.setOnClickListener {
            val newNoMeja = noMeja.text.toString()

            when{

                newNoMeja.isEmpty() -> alert()

                else -> {
                    val updatedMeja = Mejamas(number = newNoMeja.toInt())
                    updatedMeja.id = id
                    mejamasViewModel.updateTable(updatedMeja)
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