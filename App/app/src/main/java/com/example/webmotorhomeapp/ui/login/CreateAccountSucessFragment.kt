package com.example.webmotorhomeapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import com.example.webmotorhomeapp.databinding.FragmentCreateAccountSucessBinding

class CreateAccountSucessFragment: Fragment() {

    private lateinit var binding: FragmentCreateAccountSucessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountSucessBinding.inflate(inflater, container, false)
        return binding.root
    }
}


//@Composable
//fun YellowButton(text: String) {
//    Button(
//        onClick = {},
//        modifier = Modifier.
//            fillMaxWidth()
//            .fillMaxHeight()
//            .background(color = Color(0xFFFFC700), shape = RoundedCornerShape(size = 16.dp))
//    ){
//        Text(text)
//    }
//}

