package com.example.kasirmas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mejamas(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var number : Int
)
