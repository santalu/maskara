package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.santalu.maskedittext.MaskEditText;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MaskEditText etCreditCart = findViewById(R.id.et_credit_cart);
        final MaskEditText etPhoneNumber = findViewById(R.id.et_phone_number);
        final MaskEditText etDate = findViewById(R.id.et_date);
        final TextView tvCreditCart = findViewById(R.id.tv_credit_cart);
        final TextView tvPhoneNumber = findViewById(R.id.tv_phone_number);
        final TextView tvDate = findViewById(R.id.tv_date);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                tvCreditCart.setText(String.format("%s %s", getString(R.string.hint_credit_cart_number), etCreditCart.getRawText()));
                tvPhoneNumber.setText(String.format("%s %s", getString(R.string.hint_phone_number), etPhoneNumber.getRawText()));
                tvDate.setText(String.format("%s %s", getString(R.string.hint_date), etDate.getRawText()));
            }
        });
    }
}
