package com.droidProject.breakingbad.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters(
    var id: Int,
    var name: String,
    var birthday: String,
    var occupation:String,
    var img: String,
    var status: String,
    var nickname: String,
    var appearance: String,
    var portrayed: String,
    var category:String
): Parcelable