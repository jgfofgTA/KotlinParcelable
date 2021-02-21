package com.example.kotlinparcelable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SignInModel(var login: String, var password: String): Parcelable {}