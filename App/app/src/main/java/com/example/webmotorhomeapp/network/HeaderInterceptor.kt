package com.example.webmotorhomeapp.network

import com.example.webmotorhomeapp.data.login.AuthToken
import okhttp3.Interceptor
import okhttp3.Response
import java.net.Authenticator

//class HeaderInterceptor: Authenticator, Interceptor {
//
//    private val authHeader = "Authorization"
//
//    private var token = ""
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//
//        val req = chain.request()
//        val authReceived = req.headers(authHeader)
//        authReceived?.let {
//            if(it.isEmpty()) {
//                val session = getToken()
//            }
//        }
//    }
//
//    private fun getToken(): AuthToken? {
//        val token =
//    }
//
//
//}