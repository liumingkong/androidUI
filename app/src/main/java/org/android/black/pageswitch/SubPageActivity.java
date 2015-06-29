package org.android.black.pageswitch;

import android.os.Bundle;

import org.android.black.R;

import widget.base.ui.BaseActivity;

/**
 * Created by liumingkong on 15/6/24.
 */
public class SubPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sub);
        setBackBtn();
    }
}
