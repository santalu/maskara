package com.santalu.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santalu.maskedittext.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_main.etCreditCart
import kotlinx.android.synthetic.main.activity_main.etCustom
import kotlinx.android.synthetic.main.activity_main.etDate
import kotlinx.android.synthetic.main.activity_main.etPhoneNumber
import kotlinx.android.synthetic.main.activity_main.show
import kotlinx.android.synthetic.main.activity_main.tvCreditCart
import kotlinx.android.synthetic.main.activity_main.tvCustom
import kotlinx.android.synthetic.main.activity_main.tvDate
import kotlinx.android.synthetic.main.activity_main.tvPhoneNumber

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val textWatcher = MaskTextWatcher(etCustom, "#### ####")
    etCustom.addTextChangedListener(textWatcher)

    show.setOnClickListener {
      tvCustom.text = textWatcher.unformat(etCustom.text)
      tvCreditCart.text = etCreditCart.rawText
      tvPhoneNumber.text = etPhoneNumber.rawText
      tvDate.text = etDate.rawText
    }
  }
}
