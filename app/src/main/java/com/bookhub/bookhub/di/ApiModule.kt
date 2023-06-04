package com.bookhub.bookhub.di

import android.content.Context
import com.bookhub.bookhub.api.BookApi
import com.bookhub.bookhub.api.AuthApi
import com.bookhub.bookhub.api.AuthRepo
import com.bookhub.bookhub.api.BookRepo
import com.bookhub.bookhub.utils.LocalStorageUtil
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

const val BASE_URL = "http://10.0.2.2:8080/api/v1/"

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideMoshi() : Moshi{
        return Moshi.Builder()
            //.add(Rfc3339DateJsonAdapter())
            .build()
    }

    @Provides
    fun provideOkHttp(localStorageUtil : LocalStorageUtil) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor {
                val token = runBlocking { localStorageUtil.getToken().first() }
                val builder = it.request().newBuilder()
                builder.header("Authorization", "Bearer $token")
                return@addInterceptor it.proceed(builder.build())
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideLocalStorageUtil(@ApplicationContext applicationContext: Context) : LocalStorageUtil{
        return LocalStorageUtil(applicationContext)
    }

    @Provides
    fun provideAuthApi(retrofit : Retrofit) : AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun providedAuthRepo(authApi: AuthApi, localStorageUtil: LocalStorageUtil) : AuthRepo{
        return AuthRepo(authApi,localStorageUtil)
    }

    @Provides
    fun provideBookApi(retrofit: Retrofit) : BookApi{
        return retrofit.create(BookApi::class.java)
    }

    @Provides
    fun provideBookRepo(bookApi: BookApi) : BookRepo{
        return BookRepo(bookApi)
    }
}