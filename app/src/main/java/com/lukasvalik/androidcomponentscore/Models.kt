package com.lukasvalik.androidcomponentscore

import com.google.gson.annotations.SerializedName

data class Airline(
        @SerializedName("__clazz")
        val clazz: String,
        val code: String,
        val codeDefaultName: String,
        val logoUrl: String,
        val name: String,
        val phone: String,
        val site: String,
        val usName: String)