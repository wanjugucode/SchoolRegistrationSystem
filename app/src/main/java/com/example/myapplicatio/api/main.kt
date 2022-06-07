package com.example.myapplicatio.api

//kotlin extension function allows us toextend functions
fun main(){
    val name = "GisembaJuliet"
    println(name.shorten())
}
fun String.shorten():String{
    return this.substring(0,2)
}