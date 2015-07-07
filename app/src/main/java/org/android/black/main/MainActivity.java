package org.android.black.main;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import widget.base.ui.BaseActivity;

import org.android.black.R;
import org.android.black.pageswitch.MainPageActivity;

import org.android.black.phoenix.PhoenixActivity;
import org.android.black.pulltozoom.PullToZoomActivity;
import org.android.black.quickreturn.QuickReturnActivity;
import org.android.black.viewpager.ViewPagerActivity;
import org.android.black.waveview.WaveViewActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.quickreturn_cv)
    public void onQuickReturn() {
        startActivity(QuickReturnActivity.class);
    }

    @OnClick(R.id.page_change_show_cv)
    public void onPageChange() {
        startActivity(MainPageActivity.class);
    }
    
    @OnClick(R.id.waveview_cv)
    public void onWaveView() {startActivity(WaveViewActivity.class);}

    @OnClick(R.id.pull_to_zoom_listview_cv)
    public void onPullToZoom() {startActivity(PullToZoomActivity.class);}

    @OnClick(R.id.phoenix_view)
    public void onPhoenixview() {startActivity(PhoenixActivity.class);}

    @OnClick(R.id.viewpager_cv)
    public void onViewPager() {startActivity(ViewPagerActivity.class);}
}
