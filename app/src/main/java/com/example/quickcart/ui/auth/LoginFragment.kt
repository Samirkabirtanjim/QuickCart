package com.example.quickcart.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.quickcart.R
import com.example.quickcart.model.RetrofitClient
import com.example.quickcart.api.auth.LoginRequest
import com.example.quickcart.api.Login_Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        emailEditText = view.findViewById(R.id.editText_name)
        passwordEditText = view.findViewById(R.id.password_edittext)
        loginButton = view.findViewById(R.id.btn)

        loginButton.setOnClickListener {
            handleLogin()
        }

        return view
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            val message = "Please fill all fields"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            return
        }

        loginUser(email, password)
    }

    private fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        RetrofitClient.api.loginUser(loginRequest).enqueue(object : Callback<Login_Response> {
            override fun onResponse(call: Call<Login_Response>, response: Response<Login_Response>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val message = loginResponse?.message ?: "Login successful"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    val message = "Login failed: $errorMessage"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Login_Response>, t: Throwable) {
                val message = "Login failed: ${t.message}"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}
