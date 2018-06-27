package com.github.ramonrabello.cookpadassignment.core.ktx

import android.graphics.Typeface
import android.widget.TextView
import com.github.ramonrabello.cookpadassignment.view.BaseAdapter

/**
 * Customize the font according to [fontName].
 */
fun TextView.toTypeface(fontName: String) {
    this.typeface = Typeface.createFromAsset(this.context.assets, "fonts/$fontName.ttf")
}