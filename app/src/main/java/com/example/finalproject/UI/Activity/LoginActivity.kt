package com.example.finalproject.UI.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finalproject.R

class LoginActivity : AppCompatActivity(),TextWatcher {
    lateinit var loginEmail: EditText
    lateinit var loginPassword: EditText
    lateinit var loginBtn: Button
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initial()

        checkStart()

        loginAction()

    }
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if(loginPassword.text.isNotEmpty()&&loginEmail.text.isNotEmpty())
            loginBtn.isEnabled=true

    }

    override fun afterTextChanged(p0: Editable?) {

    }

    fun initial(){
        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.loginBtn)
        sharedPreferences = getSharedPreferences("name",0)
    }

    fun loginAction(){
        loginEmail.addTextChangedListener(this)
        loginPassword.addTextChangedListener(this)
        loginBtn.setOnClickListener {

            if(loginEmail.text.toString()=="appssquare@gmail.org"&&loginPassword.text.toString()=="123456789"&& Patterns.EMAIL_ADDRESS.matcher(loginEmail.text.toString()).matches()) {
                var edit : SharedPreferences.Editor = sharedPreferences.edit()
                edit.putBoolean("bool",true)
                edit.commit()
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
                Toast.makeText(this,"Email or password wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkStart(){
        var startWithRestaurantList = sharedPreferences.getBoolean("bool",false)
        if (startWithRestaurantList){
            var moveToRestaurantList = Intent(this,MainActivity::class.java)
            startActivity(moveToRestaurantList)
            finish()
        }
    }


}