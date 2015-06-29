package widget.view.quickreturn.demo;

import android.os.Bundle;

import org.android.black.R;

import widget.base.ui.BaseActivity;

/**
 * Created by liumingkong on 15/6/29.
 */
public class QuickReturnFragActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_return_frag);
        setBackBtn();

        QuickReturnType quickReturnType = QuickReturnType.valueOf(getIntent().getStringExtra(QuickReturnType.QRT));

        if (savedInstanceState == null) {
            if (QuickReturnType.GOOGLE == quickReturnType) {
                this.getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, QuickReturnTabFragment.newInstance(quickReturnType))
                        .commit();
            }
        }


    }
}
