package com.example.familysafety

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity
data class InviteModel(

    val name: String,
    @PrimaryKey
    val number: String
)
{
    constructor() : this("","")
}
