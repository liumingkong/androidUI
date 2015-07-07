package org.android.black.viewpager;

import android.os.Bundle;

import org.android.black.R;

import butterknife.OnClick;
import widget.base.ui.BaseActivity;

/**
 * Created by liumingkong on 15/7/7.
 */
public class ViewPagerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_main);
        setBackBtn();

    }

    @OnClick(R.id.fancy_cover_cv)
    public void onFancyCoverView() {startActivity(FancyCoverActivity.class);}
}
