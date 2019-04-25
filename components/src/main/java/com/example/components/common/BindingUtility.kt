package com.example.components.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.components.R
import com.google.android.material.textfield.TextInputLayout

/**
 * Binding function to set [String] error message
 * @author Sumit Lakra
 * @param [view] TextInputLayout
 * @param [errorMessage] String
 * @date 04/25/2019
 */
@BindingAdapter(value = ["errorText"])
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}

/**
 * Binding function to set [Int] error message
 * @author Sumit Lakra
 * @param [view] TextInputLayout
 * @param [errorMessage] Int
 * @date 04/25/2019
 */
@BindingAdapter(value = ["errorText"])
fun setErrorMessageInt(view: TextInputLayout, errorMessage: Int?) {
    view.error = errorMessage?.let { view.context.getString(errorMessage)}
}

/**
 * Binding function to load image into ImageView.
 * @author Sumit Lakra
 * @param [view] ImageView
 * @param [imagePath] String
 * @param [placeHolderDrawable] Drawable
 * @param [signature] String
 * @date 04/25/19
 */
@BindingAdapter(value = ["imagePath", "placeHolderDrawable", "signature"], requireAll = false)
fun setLoadImageWithGlide(view: ImageView, imagePath: String?, placeHolderDrawable: Drawable?, signature: String?) {
    var glideImagePath = imagePath
    var glidePlaceHolder = placeHolderDrawable

    if (glideImagePath == null) {
        glideImagePath = ""
    }

    if (glidePlaceHolder == null) {
        glidePlaceHolder = view.context.resources.getDrawable(R.drawable.image_placeholder, null)
    }

    val signatureStr = signature ?: ""

    Glide.with(view.context)
        .load(glideImagePath)
        .signature(ObjectKey(signatureStr))
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(glidePlaceHolder)
                .centerCrop()
        )
        .into(view)
}

/**
 * Binding function to load circular image into ImageView
 * @param [view] ImageView
 * @param [imageUri] String
 * @param [error] Drawable
 * @param [signature] String
 * @author Sumit Lakra
 * @date 04/25/2019
 */
@BindingAdapter(value = ["circularImageUri", "error", "signature"], requireAll = false)
fun loadCircularImage(view: ImageView, imageUri: String?, error: Drawable?, signature: String?) {
    var glideImagePath = imageUri
    var glidePlaceHolder = error

    if (glideImagePath == null) {
        glideImagePath = ""
    }

    if (glidePlaceHolder == null) {
        glidePlaceHolder = view.context.resources.getDrawable(R.drawable.image_placeholder, null)
    }

    val signatureStr = signature ?: ""

    Glide.with(view.context)
        .load(glideImagePath)
        .signature(ObjectKey(signatureStr))
        .error(glidePlaceHolder)
        .apply(RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
        .signature(ObjectKey(signature ?: ""))
        .into(view)
}