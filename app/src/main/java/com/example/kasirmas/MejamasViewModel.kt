package com.example.kasirmas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MejamasViewModel (application: Application): AndroidViewModel(application) {
    val getMejamas: LiveData<List<Mejamas>>
    val repository : KasirRepo

    init {
        val dao = KasirDb.getDatabase(application).getDaoKsr()
        repository = KasirRepo(dao)
        getMejamas = repository.table
    }

    fun insertTable(mejamas: Mejamas) = viewModelScope.launch(Dispatchers.IO){
        repository.insertMejamas(mejamas)
    }

    fun deleteTable(mejamas: Mejamas) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteMejamas(mejamas)
    }

    fun updateTable(mejamas: Mejamas) = viewModelScope.launch(Dispatchers.IO){
        repository.updateMejamas(mejamas)
    }



}
