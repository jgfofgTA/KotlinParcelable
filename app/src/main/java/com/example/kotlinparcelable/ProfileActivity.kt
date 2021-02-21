package com.example.kotlinparcelable

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.profile_activity.*

class ProfileActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, signIn: SignInModel): Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                putExtra("SIGNIN",signIn)
            }
        }
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        val signIn = intent.getParcelableExtra<SignInModel>("SIGNIN")
        toolbar.title = signIn?.login
    }
}