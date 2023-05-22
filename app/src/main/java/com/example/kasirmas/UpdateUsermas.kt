package com.example.kasirmas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kasirmas.databinding.ActivityUpdateUsermasBinding

class UpdateUsermas : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUsermasBinding
    private lateinit var usermasViewModel: UsermasViewModel
    var idJob: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUsermasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usermasViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UsermasViewModel::class.java]


        supportActionBar?.hide()

        binding.updateUsermasIbBack.setOnClickListener {
            startActivity(Intent(this, DataUser::class.java))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        var job = intent.getStringExtra("jobdesc")
        val spinner = binding.updateUsermasSpJobdesc
        val name = binding.updateUsermasEtName
        val UserName = binding.updateUsermasEtUsername
        val password = binding.updateUsermasEtPassword

        idJob = when (job) {
            "Admin" -> {
                1
            }
            "Kasir" -> {
                2
            }
            else -> {
                3
            }

        }


        name.setText(intent.getStringExtra("name"))
        UserName.setText(intent.getStringExtra("UsermasName"))
        password.setText(intent.getStringExtra("password"))

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_job,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(idJob)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinner.setSelection(p2)
                job = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        binding.updateUsermasBtnSave.setOnClickListener {
            val newName = name.text.toString()
            val newUserName = UserName.text.toString()
            val newPassword = password.text.toString()

            when {

                newName.isEmpty() -> alert()
                newUserName.isEmpty() -> alert()
                newPassword.isEmpty() -> alert()
                job == "pilih" -> alert()

                else -> {
                    val updatedUsermas = Usermas(
                        name = newName,
                        job = job.toString(),
                        username = newUserName,
                        password = newPassword,
                        image = 0
                    )
                    updatedUsermas.id = id
                    usermasViewModel.updateUsermas(updatedUsermas)
                    startActivity(Intent(this, DataUser::class.java))
                    finish()


                }
            }
        }
    }
    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}