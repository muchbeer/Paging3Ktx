package raum.muchbeer.paging3ktx.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RedditInstance
{

    companion object {
        private const val BASE_URL = "https://www.reddit.com/"
        private var retrofit: Retrofit? = null

        val httpLogger = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(httpLogger)
                .connectTimeout(30, TimeUnit.SECONDS) }.build()


        fun getClient(): Retrofit {
            when (retrofit) {
                null -> retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
            }
            return retrofit as Retrofit
        }
    }
}