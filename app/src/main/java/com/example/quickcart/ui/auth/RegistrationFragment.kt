package com.example.quickcart.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.quickcart.R
import com.example.quickcart.model.RetrofitClient
import com.example.quickcart.api.auth.Registration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.quickcart.api.Registration_Response

class RegistrationFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmButton: Button
    private lateinit var termsCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        nameEditText = view.findViewById(R.id.editText_name)
        phoneEditText = view.findViewById(R.id.phone_edittext)
        emailEditText = view.findViewById(R.id.email_edittext)
        passwordEditText = view.findViewById(R.id.pass_edittext)
        confirmButton = view.findViewById(R.id.confirm_button)
        termsCheckBox = view.findViewById(R.id.checkbox)

        confirmButton.setOnClickListener {
            handleRegistration()
        }

        return view
    }

    private fun handleRegistration() {
        val name = nameEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!termsCheckBox.isChecked) {
            Toast.makeText(requireContext(), "Please accept terms and policy", Toast.LENGTH_SHORT).show()
            return
        }

        registerUser(name, phone, email, password)
    }

    private fun registerUser(name: String, phone: String, email: String, password: String) {
        val registration = Registration(email, name, password, phone)

        RetrofitClient.api.registerUser(registration).enqueue(object : Callback<Registration_Response> {
            override fun onResponse(call: Call<Registration_Response>, response: Response<Registration_Response>) {
                val message: String

                if (response.isSuccessful) {
                    message = response.body()?.message ?: "Registration successful"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    message = "Registration failed: $errorMessage"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Registration_Response>, t: Throwable) {
                val message = "Registration failed: ${t.message}"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
