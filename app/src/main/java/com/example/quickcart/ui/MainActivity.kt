package com.example.quickcart.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.quickcart.ui.auth.LoginFragment
import com.example.quickcart.R
import com.example.quickcart.ui.auth.RegistrationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            loginfragment(LoginFragment())
        }

        val loginButton = findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {
            loginfragment(LoginFragment())  // Load the FreeFragment when clicked
        }

        val RegistrationButton = findViewById<Button>(R.id.registration)
        RegistrationButton.setOnClickListener {
            Registrationfragment(RegistrationFragment())  // Load the FixedFragment when clicked
        }

    }

    private fun loginfragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)  // frameLayout is the container for the fragment
        transaction.commit()
    }

    private fun Registrationfragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)  // frameLayout is the container for the fragment
        transaction.commit()
    }


}
