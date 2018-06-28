package com.santalu.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.etCreditCart
import kotlinx.android.synthetic.main.activity_main.etDate
import kotlinx.android.synthetic.main.activity_main.etPhoneNumber
import kotlinx.android.synthetic.main.activity_main.show
import kotlinx.android.synthetic.main.activity_main.tvCreditCart
import kotlinx.android.synthetic.main.activity_main.tvDate
import kotlinx.android.synthetic.main.activity_main.tvPhoneNumber

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    show.setOnClickListener {
      tvCreditCart.text = etCreditCart.text
      tvPhoneNumber.text = etPhoneNumber.text
      tvDate.text = etDate.text
    }
  }
}
