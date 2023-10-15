package com.example.webmotorhomeapp.repository.users

import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.data.user.User
import com.example.webmotorhomeapp.network.WebMotorhomeModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo {

    private val _listUser = MutableStateFlow<List<User>?>(null)
    val users: StateFlow<List<User>?> = _listUser
    private val service = WebMotorhomeModule.serviceUsers()

    fun getAllItems(callback: (List<User>?, Throwable?) -> Unit) {
        val call = service.getAll()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    _listUser.value = response.body()
                } else callback(null, Exception("Erro ao carregadar dados"))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}