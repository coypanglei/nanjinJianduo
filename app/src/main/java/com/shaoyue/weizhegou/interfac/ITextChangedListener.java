package com.shaoyue.weizhegou.interfac;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by bin on 16/10/23.
 */

public abstract class ITextChangedListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
