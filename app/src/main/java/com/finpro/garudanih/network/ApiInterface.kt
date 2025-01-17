package com.finpro.garudanih.network

import com.finpro.garudanih.model.*
import com.finpro.garudanih.model.order.DataOrderPP
import com.finpro.garudanih.model.responsedetail.ResponseDetailTiket
import com.finpro.garudanih.model.responsenotif.DataNotify
import com.finpro.garudanih.model.updatepaid.ResponsePaid
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("v1/user/register")
    fun registerUser(@Body request : DataClassUser): Call<DataUserResponse>

    @POST("v1/user/login")
    fun loginUser(@Body userLogin : UserLogin): Call<ResponseUserLogin>

    @GET("v1/user/current")
    fun getUserLogin(@Header("Authorization")authHeader : String): Call<ResponseUserCurrent>

    @GET("v1/ticket-doms")
    fun getAllListTicket() : Call<ResponseListTiket>

    @GET("v1/ticket-intr")
    fun getAllListTicketIntr() : Call<ResponseListTiket>

    @GET("v1/ticket/{id}")
    fun getDetailByid(@Header("Authorization")authHeader : String
                     ,@Path("id") id : Int): Call<ResponseDetailTiket>

    @PUT("/v1/user/update")
    fun updateUserLogin(@Header("Authorization")auth: String,
                        @Body user : UpdateProfile):Call<ResponseUserUpdate>

    @POST("v1/trans/{ticketId}")
    fun orderTiket(@Header("Authorization")auth : String,
                   @Path("ticketId") ticketId : Int,
                   @Body request: DataOrder): Call<ResponseOrder>

    @POST("v1/trans/{ticketId}")
    fun orderTiketPP(@Header("Authorization")auth : String,
                   @Path("ticketId") ticketId : Int,
                   @Body request: DataOrderPP): Call<ResponseDetailTiket>

    @GET("v1/user/history")
    fun getHistoryPemesanan(@Header("Authorization")authHeader : String) : Call<HistoryResponse>

    @PUT("v1/trans/paid/{transId}")
    fun updatePaid(@Path("transId") transId : Int): Call<ResponsePaid>

    @PUT("/v1/user/update")
    @Multipart
    fun editProfile(
        @Header("Authorization")auth: String,
        @Part("name") name: RequestBody,
        @Part fileImage : MultipartBody.Part,
        @Part("phone") phone: RequestBody,
        @Part("birth") birth: RequestBody,
        @Part("city") city: RequestBody,
    ) : Call<ResponseUserUpdate>

    @GET("v1/user/notify")
    fun getNotify(@Header("Authorization")auth: String): Call<DataNotify>

}