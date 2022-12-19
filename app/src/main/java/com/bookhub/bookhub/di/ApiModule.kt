package com.bookhub.bookhub.di

import com.bookhub.bookhub.api.LoginApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "insert_base_url_here"

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideMoshi() : Moshi{
        return Moshi.Builder().build()
    }

    @Provides
    fun provideRetrofit(moshi : Moshi) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideLoginApi(retrofit : Retrofit) : LoginApi{
        return retrofit.create(LoginApi::class.java)
    }
}