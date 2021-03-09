package dev.ebnbin.ebdev

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DevPage(
    val title: String,
    val fragmentClassName: String,
    val fragmentArgs: Bundle = Bundle.EMPTY,
) : Parcelable
