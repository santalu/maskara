package com.santalu.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.etCreditCart
import kotlinx.android.synthetic.main.activity_main.etDate
import kotlinx.android.synthetic.main.activity_main.etPhoneNumber
import kotlinx.android.synthetic.main.activity_main.etToken
import kotlinx.android.synthetic.main.activity_main.show
import kotlinx.android.synthetic.main.activity_main.tvCreditCart
import kotlinx.android.synthetic.main.activity_main.tvDate
import kotlinx.android.synthetic.main.activity_main.tvPhoneNumber
import kotlinx.android.synthetic.main.activity_main.tvToken

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    show.setOnClickListener {
      tvToken.text = etToken.rawText
      tvCreditCart.text = etCreditCart.rawText
      tvPhoneNumber.text = etPhoneNumber.rawText
      tvDate.text = etDate.rawText
    }
  }
}
