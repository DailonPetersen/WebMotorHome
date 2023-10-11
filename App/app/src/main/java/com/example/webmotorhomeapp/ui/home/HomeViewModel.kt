package com.example.webmotorhomeapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.webmotorhomeapp.network.ApiService


class HomeViewModel(private val api: ApiService) : ViewModel() {

//    val repo = RepositoryUser(api)
//
//    private val _users = MutableLiveData<List<User>>()
//
//    fun getUsers(): LiveData<Result<List<User>?>> {
//        Log.e("DAILON", "repo $repo")
//        repo.buscaUsers(defaultCallback())
//    }


    private fun defaultCallback(result: Boolean, message: String?, except: Throwable?): Unit {
        when(result) {
            true -> Log.e("DAILON", "user $message")
            false -> Log.e("DAILON", "user $message")
            else -> {}
        }
    }
}

//class RepositoryUser (private val service: WebmotohomeApiService){
//    fun buscaUsers(callback: (result: Boolean, message: List<User>?, except: Throwable?) -> Unit) {
//        CoroutineScope(Dispatchers.IO).launch{
//            service.getAllUsers().enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        val body = response.body()?.string()!!
//                        val type = object : TypeToken<List<User>>() {}.type
//                        val users: List<User> = Gson().fromJson(body, type)
//                        callback(true, users, null)
//                    } else {
//                        val body = response.errorBody()?.string()
//                        callback(false, null, null )
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    callback(false, null , t)
//                }
//
//            })
//        }
//
//    }
//}