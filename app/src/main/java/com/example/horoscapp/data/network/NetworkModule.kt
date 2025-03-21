package com.example.horoscapp.data.network

import com.example.horoscapp.data.HoroscopeRepositoryImpl
import com.example.horoscapp.domain.HoroscopeRepository
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
// Marca el alcance de este móudlo. En este caso, todo el mundo puede inyectarse este módulo
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newastro.vercel.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit:Retrofit):HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }

    @Provides
    fun provideHoroscopeRepository(horoscopeApiService: HoroscopeApiService):HoroscopeRepository {
        return HoroscopeRepositoryImpl(horoscopeApiService)
    }
}