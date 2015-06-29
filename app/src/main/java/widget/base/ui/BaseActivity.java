package widget.base.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.black.common.utils.Utils;
import com.squareup.otto.Bus;

import org.android.black.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import widget.base.BaseUtils;
import widget.base.DaggerInjector;
import widget.base.Ln;
import widget.view.pageswitch.PageSwitchUtils;

/**
 * Created by liumingkong on 15/6/12.
 */
public class BaseActivity extends FragmentActivity {

    private String pageTag;

    @Inject
    public Bus bus;

    @Optional
    @InjectView(R.id.common_header_left_btn_rlv)
    View common_header_left_btn_rlv;

    @Optional
    @InjectView(R.id.common_header_title_tv)
    TextView common_header_title_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DaggerInjector.inject(this);//依赖注入
        bus.register(this);
        getWindow().setBackgroundDrawable(null); // 设置背景为空
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        ButterKnife.inject(this);
    }

    // 设置返回键生效
    protected void setBackBtn() {
        if (!Utils.isNull(common_header_left_btn_rlv)) {
            common_header_left_btn_rlv.setVisibility(View.VISIBLE);
            common_header_left_btn_rlv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    // ****************************** 基础方法 ***********************************
    // 采用懒加载的方式，标识当前页面
    protected String getPageTag() {
        if (Utils.isNull(this.pageTag)) {
            this.pageTag = BaseUtils.genPageTag(this.getClass().getName());
        }
        return this.pageTag;
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        Ln.d("BasicActivity finish");
        PageSwitchUtils.exitPageAnim(this);//退出动画
    }
}
