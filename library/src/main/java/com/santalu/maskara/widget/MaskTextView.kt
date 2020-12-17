package com.santalu.maskara.widget

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.santalu.maskara.*

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

    private var maskChangedListener: MaskChangedListener? = null

    val masked: String
        get() = maskChangedListener?.masked.orEmpty()

    val unMasked: String
        get() = maskChangedListener?.unMasked.orEmpty()

    val isDone: Boolean
        get() = maskChangedListener?.isDone ?: false

    private fun init(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.MaskEditText).apply {
            val style = getInteger(R.styleable.MaskEditText_maskStyle, 0)
            val value = getString(R.styleable.MaskEditText_mask).orEmpty()
            val character = getString(R.styleable.MaskEditText_maskCharacter).orEmpty()

            if (value.isNotEmpty()) {
                val maskChar = if (character.isEmpty()) value.mostOccurred() else character.single()
                val mask = Mask(value, maskChar, MaskStyle.valueOf(style))
                maskChangedListener = MaskChangedListener(mask)
            }

            recycle()
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
