package com.santalu.maskara.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.santalu.maskara.Action
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskResult
import com.santalu.maskara.MaskStyle
import com.santalu.maskara.Maskara
import com.santalu.maskara.R
import com.santalu.maskara.mostOccurred

@SuppressLint("Recycle")
class MaskTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private var maskara: Maskara? = null
    private var result: MaskResult? = null

    val masked: String
        get() = result?.masked.orEmpty()

    val unMasked: String
        get() = result?.unMasked ?: text.toString()

    val isDone: Boolean
        get() = result?.isDone ?: false

    private fun init(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.MaskEditText).apply {
            val style = getInteger(R.styleable.MaskEditText_maskStyle, 0)
            val value = getString(R.styleable.MaskEditText_mask).orEmpty()
            val character = getString(R.styleable.MaskEditText_maskCharacter).orEmpty()

            if (value.isNotEmpty()) {
                val maskChar = if (character.isEmpty()) value.mostOccurred() else character.single()
                val mask = Mask(value, maskChar, MaskStyle.valueOf(style))
                maskara = Maskara(mask)
            }

            recycle()
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val masked = text?.let {
            result = maskara?.apply(text, Action.INSERT)
            result?.masked
        } ?: text
        super.setText(masked, type)
    }

    fun clearMask() {
        maskara = null
    }
}
