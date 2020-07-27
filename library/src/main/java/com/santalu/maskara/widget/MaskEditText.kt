package com.santalu.maskara.widget

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import com.google.android.material.textfield.TextInputEditText
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskChangedListener
import com.santalu.maskara.MaskStyle
import com.santalu.maskara.R
import com.santalu.maskara.mostOccurred

/**
 * Created by fatih.santalu on 7/7/2020.
 */

class MaskEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr) {

    private var maskChangedListener: MaskChangedListener? = null

    val masked: String
        get() = maskChangedListener?.masked.orEmpty()

    val unMasked: String
        get() = maskChangedListener?.unMasked.orEmpty()

    val isDone: Boolean
        get() = maskChangedListener?.isDone ?: false

    init {
        context.withStyledAttributes(attrs, R.styleable.MaskEditText) {
            val style = getInteger(R.styleable.MaskEditText_maskStyle, 0)
            val value = getString(R.styleable.MaskEditText_mask).orEmpty()
            val character = getString(R.styleable.MaskEditText_maskCharacter).orEmpty()

            if (value.isNotEmpty()) {
                val maskChar = if (character.isEmpty()) value.mostOccurred() else character.single()
                val mask = Mask(value, maskChar, MaskStyle.valueOf(style))
                maskChangedListener = MaskChangedListener(mask)
            }
        }
    }

    /**
     * Let only one [maskChangedListener] allowed at a time
     */
    override fun addTextChangedListener(watcher: TextWatcher?) {
        if (watcher is MaskChangedListener) {
            removeTextChangedListener(maskChangedListener)
            maskChangedListener = watcher
        }
        super.addTextChangedListener(watcher)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addTextChangedListener(maskChangedListener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeTextChangedListener(maskChangedListener)
    }
}
