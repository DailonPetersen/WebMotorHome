package com.example.webmotorhomeapp.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.webmotorhomeapp.data.user.User
import com.example.webmotorhomeapp.repository.users.UserRepo
import kotlinx.coroutines.flow.StateFlow

class CompleteProfileViewModel : ViewModel() {

    private val userRepo = UserRepo()
    var users: StateFlow<List<User>?> = userRepo.users

    fun fetchItems() {
        userRepo.getAllItems { _list, error ->
            if (_list != null) {
                Log.e("DAILON", "lista $_list")
            } else {
                Log.e("DAILON", "error $error")
            }
        }
    }
}