package dev.iamspathan.realworldio.extensions

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun ImageView.loadImage(url: String, circleCrop: Boolean = false) {
    if (circleCrop) {
        Glide.with(this).load(url).circleCrop().into(this)
    } else {
        Glide.with(this).load(url).into(this)
    }
}


