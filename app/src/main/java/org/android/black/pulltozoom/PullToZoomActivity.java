package org.android.black.pulltozoom;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import org.android.black.R;

import butterknife.InjectView;
import widget.base.ui.BaseActivity;
import widget.view.pushtozoom.PullToZoomListView;

/**
 * Created by liumingkong on 15/7/1.
 */
public class PullToZoomActivity extends BaseActivity {

    @InjectView(R.id.listview)
    protected PullToZoomListView pullToZoomListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_listview);
        setBackBtn();

        String[] adapterData = new String[] { "Activity","Service","Content Provider","Intent",
                "BroadcastReceiver","ADT","Sqlite3","HttpClient","DDMS",
                "Android Studio","Fragment","Loader" };

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(PullToZoomActivity.this, android.R.layout.simple_list_item_1, adapterData);
        pullToZoomListView.setAdapter(arrayAdapter);
        pullToZoomListView.getHeaderView().setImageResource(R.drawable.splash01);
        pullToZoomListView.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);

    }
}
