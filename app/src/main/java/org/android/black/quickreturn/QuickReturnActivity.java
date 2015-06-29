package org.android.black.quickreturn;

import android.content.Intent;
import android.os.Bundle;
import org.android.black.R;
import butterknife.OnClick;
import widget.base.ui.BaseActivity;

/**
 * Created by liumingkong on 15/6/24.
 */
public class QuickReturnActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_return);
        setBackBtn();
    }

    @OnClick(R.id.qr_twitter_cv)
    public void onTwitterCv() {
        Intent intent = new Intent(this, QuickReturnTabActivity.class);
        intent.putExtra(QuickReturnType.QRT, QuickReturnType.TWITTER.name());
        startActivity(intent);
    }

    @OnClick(R.id.qr_facebook_cv)
    public void onFacebookCv() {
        Intent intent = new Intent(this, QuickReturnTabActivity.class);
        intent.putExtra(QuickReturnType.QRT, QuickReturnType.FACEBOOK.name());
        startActivity(intent);
    }

    @OnClick(R.id.qr_google_cv)
    public void onGoogleCv() {
        Intent intent = new Intent(this, QuickReturnFragActivity.class);
        intent.putExtra(QuickReturnType.QRT, QuickReturnType.GOOGLE.name());
        startActivity(intent);
    }

    @OnClick(R.id.qr_listview_cv)
    public void onListCv() {
        Intent intent = new Intent(this, QuickReturnTabActivity.class);
        intent.putExtra(QuickReturnType.QRT, QuickReturnType.LISTVIEW.name());
        startActivity(intent);
    }

    @OnClick(R.id.qr_scrollview_cv)
    public void onScrollCv() {
        Intent intent = new Intent(this, QuickReturnTabActivity.class);
        intent.putExtra(QuickReturnType.QRT, QuickReturnType.SCROLLVIEW.name());
        startActivity(intent);
    }

    @OnClick(R.id.qr_webview_cv)
    public void onWebviewCv() {
        Intent intent = new Intent(this, QuickReturnTabActivity.class);
        intent.putExtra(QuickReturnType.QRT, QuickReturnType.WEBVIEW.name());
        startActivity(intent);
    }

}
