package com.finpro.garudanih.model.responsedetail


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("arrive")
    val arrive: String,
    @SerializedName("bookingBy")
    val bookingBy: List<BookingBy>,
    @SerializedName("class")
    val classX: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("departureCode")
    val departureCode: String,
    @SerializedName("destination")
    val destination: String,
    @SerializedName("destinationCode")
    val destinationCode: String,
    @SerializedName("flight")
    val flight: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("takeOff")
    val takeOff: String,
    @SerializedName("totalChair")
    val totalChair: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)