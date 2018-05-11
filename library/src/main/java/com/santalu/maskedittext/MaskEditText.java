package com.santalu.maskedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by santalu on 09/08/2017.
 */

public class MaskEditText extends AppCompatEditText implements TextWatcher {

  private static final char REPLACE_CHAR = '#';
  private static final char EMPTY_CHAR = ' ';

  private CharSequence mask;
  private boolean updating;
  private boolean deleting;

  public MaskEditText(Context context) {
    super(context);
  }

  public MaskEditText(Context context, String mask) {
    super(context);
    setMask(mask);
  }

  public MaskEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public MaskEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MaskEditText);
    try {
      setMask(a.getString(R.styleable.MaskEditText_met_mask));
    } finally {
      a.recycle();
    }
  }

  private void applyMask(Editable e) {
    if (TextUtils.isEmpty(e) || !hasMask()) {
      return;
    }

    //save cursor position
    int cursorPosition = getSelectionStart();

    //remove input filters to ignore input type
    InputFilter[] filters = e.getFilters();
    e.setFilters(new InputFilter[0]);

    StringBuilder sb = new StringBuilder(getUnMaskedText(e.toString()));
    e.clear();

    int maskLen = mask.length();

    for (int i = 0; i < maskLen && sb.length() > 0; i++) {
      char m = mask.charAt(i);
      char t = sb.charAt(0);
      if (m == REPLACE_CHAR) {
        e.append(t);
        sb.deleteCharAt(0);
      } else {
        e.append(m);
      }
    }

    //reset filters
    e.setFilters(filters);

    int currentTextLen = e.length();
    //check if deleting
    if (deleting && cursorPosition < currentTextLen) {
      setSelection(cursorPosition);
      deleting = false;
    }
  }

  public boolean hasMask() {
    return !TextUtils.isEmpty(mask);
  }

  public boolean isUpdating() {
    return updating;
  }

  public String getRawText() {
    String text = String.valueOf(super.getText());
    return getUnMaskedText(text);
  }

  private String getUnMaskedText(String text) {
    if (TextUtils.isEmpty(text) || !hasMask()) {
      return text;
    }

    int maskLen = mask.length();
    int textLen = text.length();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < maskLen && i < textLen; i++) {
      char m = mask.charAt(i);
      char t = text.charAt(i);
      if (t != EMPTY_CHAR && t != m) {
        sb.append(t);
      }
    }

    return sb.toString();
  }

  public void setMask(CharSequence mask) {
    this.mask = mask;

    if (hasMask()) {
      setMaxLength(mask.length());
      addTextChangedListener(this);

      //detect delete
      setOnKeyListener((v, keyCode, event) -> {
        deleting = event.getAction() == KeyEvent.ACTION_DOWN
            && event.getKeyCode() == KeyEvent.KEYCODE_DEL;
        return false;
      });
    }
  }

  public void setMaxLength(int length) {
    setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    if (updating || !hasMask()) {
      return;
    }

    updating = true;
    applyMask(s);
    updating = false;
  }
}
