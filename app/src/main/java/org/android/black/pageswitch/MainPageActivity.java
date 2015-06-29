package org.android.black.pageswitch;

import android.content.Intent;
import android.os.Bundle;

import org.android.black.R;

import butterknife.OnClick;
import widget.base.ui.BaseActivity;
import widget.view.pageswitch.PageSwitchType;
import widget.view.pageswitch.PageSwitchUtils;

/**
 * Created by liumingkong on 15/6/24.
 */
public class MainPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_main);
        setBackBtn();
    }

    @OnClick(R.id.page_change_zoom_cv)
    public void pageChangeZoom() {
        switchActivity(PageSwitchType.ZOOM);
    }

    @OnClick(R.id.page_change_fade_cv)
    public void pageChangeFade() {
        switchActivity(PageSwitchType.FADE);
    }

    @OnClick(R.id.page_change_slideleft_cv)
    public void pageChangeSlideLeft() {
        switchActivity(PageSwitchType.PUSH_FROM_LEFT);
    }

    @OnClick(R.id.page_change_slideright_cv)
    public void pageChangeSlideRight() {
        switchActivity(PageSwitchType.PUSH_FROM_RIGHT);
    }

    @OnClick(R.id.page_change_pushFromDown_cv)
    public void pagePushFromDown() {
        switchActivity(PageSwitchType.PUSH_FROM_DOWN);
    }

    @OnClick(R.id.page_change_pushFromUp_cv)
    public void pagePushFromUp() {
        switchActivity(PageSwitchType.PUSH_FROM_UP);
    }

    @OnClick(R.id.page_change_slideUpDown_cv)
    public void pagePushUpAndDown() {
        switchActivity(PageSwitchType.PUSH_UP_AND_DOWN);
    }

    @OnClick(R.id.page_change_rotate_1_cv)
    public void pageRotate1() {
        switchActivity(PageSwitchType.SCALE_ROTATE_1);
    }

    @OnClick(R.id.page_change_rotate_2_cv)
    public void pageRotate2() {
        switchActivity(PageSwitchType.SCALE_ROTATE_2);
    }

    @OnClick(R.id.page_change_from_topleft_cv)
    public void pageFromTopLeft() {
        switchActivity(PageSwitchType.SCALE_FROM_LEFT_TOP);
    }

    @OnClick(R.id.page_change_slideLeftRight_cv)
    public void pageSlideLeftRight() {switchActivity(PageSwitchType.PUSH_LEFT_AND_RIGHT);}

    // 页面切换的例子方法
    private void switchActivity(PageSwitchType pageSwitchType) {
        Intent intent = new Intent(this, SubPageActivity.class);
        PageSwitchUtils.setPageAnim(pageSwitchType, intent); //设置返回类型
        startActivity(intent);
        PageSwitchUtils.enterPageAnim(this, pageSwitchType); // 进入动画
    }
}
