package com.yusufmendes.zikirmatik.util.extensions

import android.view.View
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import com.yusufmendes.zikirmatik.R

fun View.showSnackbar(message: String) {
    val snackbar =
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    snackbar.setBackgroundTint(resources.getColor(R.color.success))
    snackbar.setTextColor(resources.getColor(R.color.text_color))

    val view: View = snackbar.view
    //snackbar'ın location'ı ayarlandı
    val linearParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    linearParams.setMargins(0, 0, 0, 0)
    view.layoutParams = linearParams
    snackbar.show()
}