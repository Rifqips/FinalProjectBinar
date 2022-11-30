package com.finpro.garudanih.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

//    const val BASE_URL = "https://api-ticket.up.railway.app/"
//
//    private val logging : HttpLoggingInterceptor
//        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()
//
//    val instance : ApiInterface by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        retrofit.create(ApiInterface::class.java)
//    }

    const val BASE_URL= "https://api-ticket.up.railway.app/"

    private  val logging : HttpLoggingInterceptor
        get(){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provide(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}