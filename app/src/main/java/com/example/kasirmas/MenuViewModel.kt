package com.example.kasirmas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel (application: Application): AndroidViewModel(application) {
    val getFoodmas: LiveData<List<Foodmas>>
    val getDrinkmas: LiveData<List<Drinkmas>>
    val repository : KasirRepo

    init {
        val dao = KasirDb.getDatabase(application).getDaoKsr()
        repository = KasirRepo(dao)
        getFoodmas = repository.foodmass
        getDrinkmas = repository.drinkmass
    }

    fun insertFoodmas(foodmas: Foodmas) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFoodmas(foodmas)
    }

    fun deleteFoodmas(foodmas: Foodmas) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteFoodmas(foodmas)
    }

    fun updateFoodmas(foodmas: Foodmas) = viewModelScope.launch(Dispatchers.IO){
        repository.updateFoodmas(foodmas)
    }

    fun insertDrinkmas(drinkmas: Drinkmas) = viewModelScope.launch(Dispatchers.IO){
        repository.insertDrinkmas(drinkmas)
    }

    fun deleteDrinkmas(drinkmas: Drinkmas) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteDrinkmas(drinkmas)
    }

    fun updateDrinkmas(drinkmas: Drinkmas) = viewModelScope.launch(Dispatchers.IO){
        repository.updateDrinkmas(drinkmas)
    }
}