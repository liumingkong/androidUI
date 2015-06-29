package org.android.black.main;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import widget.base.ui.BaseActivity;

import org.android.black.R;
import org.android.black.pageswitch.MainPageActivity;

import org.android.black.quickreturn.QuickReturnActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.quickreturn_cv)
    public void onQuickReturn() {
        startActivity(new Intent(this, QuickReturnActivity.class));
    }

    @OnClick(R.id.page_change_show_cv)
    public void onPageChange() {
        startActivity(new Intent(this, MainPageActivity.class));
    }
}
