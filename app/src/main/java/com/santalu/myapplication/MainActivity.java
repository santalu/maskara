package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.santalu.maskedittext.MaskEditText;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    MaskEditText etCreditCart = findViewById(R.id.et_credit_cart);
    MaskEditText etPhoneNumber = findViewById(R.id.et_phone_number);
    MaskEditText etDate = findViewById(R.id.et_date);
    TextView tvCreditCart = findViewById(R.id.tv_credit_cart);
    TextView tvPhoneNumber = findViewById(R.id.tv_phone_number);
    TextView tvDate = findViewById(R.id.tv_date);
    Button button = findViewById(R.id.button);

    button.setOnClickListener(view -> {
      tvCreditCart.setText(String.format("%s %s", getString(R.string.hint_credit_cart_number),
          etCreditCart.getRawText()));
      tvPhoneNumber.setText(String.format("%s %s", getString(R.string.hint_phone_number),
          etPhoneNumber.getRawText()));
      tvDate.setText(String.format("%s %s", getString(R.string.hint_date), etDate.getRawText()));
    });
  }
}
