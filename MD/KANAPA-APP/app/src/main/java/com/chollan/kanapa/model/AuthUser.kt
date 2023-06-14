package com.chollan.kanapa.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthUser(val name: String, val email: String, val token: String) : Parcelable
