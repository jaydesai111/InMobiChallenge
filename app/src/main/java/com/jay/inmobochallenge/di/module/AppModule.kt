package com.jay.inmobochallenge.di.module



import android.app.Application
import androidx.room.Room
import com.jay.inmobochallenge.BuildConfig
import com.jay.inmobochallenge.data.database.PostDataBase
import com.jay.inmobochallenge.data.database.PostDatabaseDao
import com.jay.inmobochallenge.utils.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun gsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun buildRetrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()

    // region Http Clients
    @Provides
    @Singleton
    fun provideOkHttp(logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(logger)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDb(app: Application): PostDataBase {
        return Room
            .databaseBuilder(app, PostDataBase::class.java, "post_database")
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @Singleton
    @Provides
    fun providePostDatabaseDao(db: PostDataBase): PostDatabaseDao {
        return db.getPostDatabaseDao()
    }
}