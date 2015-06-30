package org.android.black.waveview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.android.black.R;

import butterknife.InjectView;
import widget.base.ui.BaseActivity;
import widget.view.waveview.WaveView;

/**
 * Created by liumingkong on 15/6/29.
 */
public class WaveViewActivity extends BaseActivity {

    @InjectView(R.id.wave_view)
    WaveView waveView;

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waveview);
        setBackBtn();

        refreshHandler.sendEmptyMessageDelayed(0, 50);
        waveView.setProgress(progress);
    }

    private Handler refreshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progress < 100) {
                progress ++;
                refreshHandler.sendEmptyMessageDelayed(0, 100);
            }
            waveView.setProgress(progress);
        }
    };
}
