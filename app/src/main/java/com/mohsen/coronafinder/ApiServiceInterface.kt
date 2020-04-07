package com.mohsen.coronafinder

import android.annotation.SuppressLint
import android.os.Build
import io.reactivex.Observable
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.TlsVersion
import okhttp3.OkHttpClient
import javax.net.ssl.SSLContext
import com.google.gson.reflect.TypeToken
import com.mohsen.coronafinder.model.Statistics
import com.mohsen.coronafinder.util.Constants
import com.mohsen.coronafinder.util.ListFromStringTypeAdapter


/**
 * Created by ogulcan on 07/02/2018.
 */
interface ApiServiceInterface {

    @GET("countries")
    fun getStatistics(): Observable<List<Statistics>>

    @GET("all")
    fun getAllStatistics(): Call<ResponseBody>

    companion object Factory {
        private var retrofit: Retrofit? = null

        @SuppressLint("ObsoleteSdkInt")
        fun create(): ApiServiceInterface {

            if (Build.VERSION.SDK_INT in 16..21) {
                retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(getNewHttpClient())
                        .build()
            } else {


                val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                        .build()
                val specs = ArrayList<ConnectionSpec>()
                specs.add(spec)
                specs.add(ConnectionSpec.MODERN_TLS)
                specs.add(ConnectionSpec.CLEARTEXT)

                val client = OkHttpClient.Builder()
                        .connectionSpecs(specs)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .connectTimeout(2, TimeUnit.MINUTES)
                        .build()

//                val gson = GsonBuilder()
//                        .setLenient()
//                        .create()

                val collectionStringType = object : TypeToken<String>() {}.type
// Create gson object
                val gson = GsonBuilder()
                        .registerTypeAdapter(collectionStringType, ListFromStringTypeAdapter())
                        .setLenient()
                        .create()

                retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()

            }
            return retrofit!!.create(ApiServiceInterface::class.java)

        }

        private fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder? {

            try {
                val sc = SSLContext.getInstance("TLSv1.2")
                sc.init(null, null, null)
                val engine = sc.createSSLEngine()
//                client.sslSocketFactory(Tls12SocketFactory(sc.socketFactory))
//
                val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build()

                val specs = ArrayList<ConnectionSpec>()
                specs.add(cs)
                specs.add(ConnectionSpec.COMPATIBLE_TLS)
                specs.add(ConnectionSpec.CLEARTEXT)

                client.connectionSpecs(specs)
            } catch (exc: Exception) {
            }

            return client
        }

        fun getNewHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .retryOnConnectionFailure(true)
                    .cache(null)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)

            return enableTls12OnPreLollipop(client)!!.build()
        }


    }
}