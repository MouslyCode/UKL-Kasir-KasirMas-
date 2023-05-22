package com.example.kasirmas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Foodmas(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var name: String,
    var description: String,
    var price: Int,
    var image: Int
)
