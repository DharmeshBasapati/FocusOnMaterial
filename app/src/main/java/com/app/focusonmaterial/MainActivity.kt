package com.app.focusonmaterial

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.app.focusonmaterial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnLogin.setOnClickListener {

                if (edtEmail.text.toString() == "test@android.com" && edtPassword.text.toString() == "123456") {

                    edtEmail.text?.clear()
                    edtPassword.text?.clear()
                    textFieldEmail.error = ""
                    textFieldPassword.error = ""
                    startActivity(Intent(this@MainActivity, RatesActivity::class.java))

                } else {

                    if (TextUtils.isEmpty(edtEmail.text)) {
                        textFieldEmail.error = "Enter Email"
                    } else if (edtEmail.text.toString() != "test@android.com") {
                        textFieldEmail.error = "Enter Correct Email"
                    }

                    if (TextUtils.isEmpty(edtPassword.text)) {
                        textFieldPassword.error = "Enter Password"
                    } else if (edtPassword.text.toString() != "123456") {
                        textFieldPassword.error = "Enter Correct Password"
                    }
                }


            }
        }

    }
}