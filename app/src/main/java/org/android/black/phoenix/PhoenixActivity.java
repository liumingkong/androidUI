package org.android.black.phoenix;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.android.black.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import widget.base.ui.BaseActivity;
import widget.listview.phoenix.PullToRefreshView;

/**
 * Created by liumingkong on 15/7/6.
 */
public class PhoenixActivity extends BaseActivity {

    @InjectView(R.id.pull_to_refresh)
    PullToRefreshView pull_to_refresh;

    @InjectView(R.id.list_view)
    ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoenix);
        setBackBtn();

        List<String> datas = new ArrayList<>();
        for (int i = 0 ; i< 20; i ++) {
            datas.add("facebook-" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item, R.id.item_tv, datas);
        list_view.setAdapter(adapter);

        pull_to_refresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pull_to_refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull_to_refresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
