package com.example.shmrapp.data.network

import com.example.data.BuildConfig
import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrfinanceapp.domain.models.CategoryModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsByPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ) : List<TransactionModel>

    @GET("transactions/{id}")
    suspend fun getTransactionById(@Path("id") id: Int): TransactionModel

    @GET("categories")
    suspend fun getCategories(): List<CategoryModel>

    @POST("transactions")
    suspend fun createTransaction(@Body request: TransactionRequest): TransactionCreateResponse
}

internal object RetrofitClient {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.BEARER_TOKEN}")
            .build()
        chain.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitApi: RetrofitApi by lazy { retrofit.create(RetrofitApi::class.java) }
}