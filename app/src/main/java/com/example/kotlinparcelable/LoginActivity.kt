package com.example.kotlinparcelable

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : AppCompatActivity() {


    private var signIn = SignInModel("", "")//создание объекта
    var passwordPattern = Regex("(?=.*[0-9])(?=.*[a-z])")//ключевые символы пароля

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        Login.addTextChangedListener(loginCheck)//передача логина
        Password.addTextChangedListener(passwordCheck)//передача пароля
        signInButton.setOnClickListener {
            SignIn()//переход в новое activity
        }
    }
    //переменные textwatcher'а
    private val loginCheck: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            signIn.login = s.toString()
            CheckSignInModel()
        }

        override fun afterTextChanged(s: Editable) {}
    }
    private val passwordCheck: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            signIn.password = s.toString()
            CheckSignInModel()
        }

        override fun afterTextChanged(s: Editable) {}
    }
    //метод управляющий кнопкой
    fun CheckSignInModel() {
        signInButton.isEnabled =
            signIn.password.length >= 8 && passwordPattern.containsMatchIn(
                signIn.password
            ) && android.util.Patterns.EMAIL_ADDRESS.matcher(signIn.login)
                .matches()
    }
    //сохранение состояния объекта
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putParcelable("SIGNIN", signIn)
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        signIn = savedInstanceState.getParcelable("SIGNIN")!!
    }
    //метод перехода в новое activity
    fun SignIn() {
        startActivity(ProfileActivity.newIntent(this, signIn))
    }
}