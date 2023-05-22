package com.example.kasirmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirmas.databinding.ActivityDatamejamasBinding
import com.example.kasirmas.databinding.ActivityMainBinding

class datamejamas : AppCompatActivity() {
    private lateinit var binding: ActivityDatamejamasBinding
    private lateinit var mejamasViewModel: MejamasViewModel
    private var table = ArrayList<Mejamas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatamejamasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mejamasViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MejamasViewModel::class.java]

        initTableRv()

        binding.dataTableIbBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataTableFabAdd.setOnClickListener {
            startActivity(Intent(this, AddMejamas::class.java))
            finish()
        }
    }
    private fun initTableRv(){


        val adapterMejamas = AdapterMejamas(this, table, mejamasViewModel)

        val rvTable = binding.dataTableRv
        rvTable.setHasFixedSize(true)
        rvTable.layoutManager = LinearLayoutManager(this)
        rvTable.adapter = adapterMejamas

        mejamasViewModel.getMejamas.observe(this) { list ->
            list?.let {
                table = it as ArrayList<Mejamas>
                adapterMejamas.updateTable(table)
            }
        }
    }
}
