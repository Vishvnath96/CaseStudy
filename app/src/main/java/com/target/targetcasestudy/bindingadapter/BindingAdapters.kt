package com.target.targetcasestudy.bindingadapter

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.target.targetcasestudy.util.StringUtil

object BindingAdapters {
    @JvmStatic
    @BindingAdapter(
        value = ["imageUrl"],
        requireAll = false
    )
    fun bindImage(
        imageView: ImageView,
        imageUrl: String
    ) {
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                loadImg(
                    imageView, imageUrl
                )
            }
        })
    }

    private fun loadImg(
        imageView: ImageView, imageUrl: String
    ) {
        var tag: String? = null
        imageView.setImageDrawable(null)
        if (StringUtil.isNullOrEmpty(imageUrl)) {

        }
        if (StringUtil.isNullOrEmpty(tag)) {
            tag = "generic_picasso_tag"
        }
        val picasso = Picasso.get()
            .load(Uri.parse(imageUrl.replace(" ", "%20")))
            .fit()
            .tag(tag!!)
            .config(Bitmap.Config.RGB_565)

        if (URLUtil.isFileUrl(imageUrl)) {
            picasso.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
        }
        picasso.into(imageView)
    }


    @BindingAdapter("strikethrough")
    @JvmStatic
    fun strikethrough(view: TextView, show: Boolean) {
        view.paintFlags = if (show) {
            view.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

}