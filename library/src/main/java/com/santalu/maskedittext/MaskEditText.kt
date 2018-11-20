package com.santalu.maskedittext

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by fatihsantalu on 20.11.2018
 */

class MaskEditText : TextInputEditText {

  var maskTextWatcher: MaskTextWatcher? = null

  var mask: String? = null
    set(value) {
      field = value
      if (value.isNullOrEmpty()) {
        removeTextChangedListener(maskTextWatcher)
      } else {
        maskTextWatcher = MaskTextWatcher(this, mask!!)
        addTextChangedListener(maskTextWatcher)
      }
    }

  val rawText: String?
    get() {
      val formatted = text
      return maskTextWatcher?.unformat(formatted) ?: formatted.toString()
    }

  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    init(context, attrs)
  }

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
      super(context, attrs, defStyleAttr) {
    init(context, attrs)
  }

  private fun init(context: Context, attrs: AttributeSet?) {
    attrs?.let {
      val a = context.obtainStyledAttributes(it, R.styleable.MaskEditText)
      with(a) {
        mask = getString(R.styleable.MaskEditText_met_mask)
        recycle()
      }
    }
  }
}