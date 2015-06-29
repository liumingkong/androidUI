package widget.view.pageswitch;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by liumingkong on 15/6/24.
 */
public class PageSwitchUtils {

    private static final String PAGE_SWITCH_KEY = "PAGE_SWITCH_KEY";

    // 可以用overridePendingTransition(R.anim.translate_in, R.anim.translate_out);这个方法切换动画
    // 但麻烦的是 切换动画时候 必须finish前面一个。
    // 采用主题定制的方式的问题是：部分机型无法适配，跳转的时候有效果，返回的时候没有效果

    // 动画坐标
    // 按照左边界为起点，原点是 页面的左下角，向上为Y正向，向右为X正向
    // 页面完全左边 -100%p, 页面填充 0, 页面完全右边 100%p
    // 页面完全下边 -100%p, 页面填充 0, 页面完全上边 100%p

    // 在Intent设置页面切换的类型
    public static void setPageAnim(PageSwitchType pageSwitchType, Intent intent) {
        intent.putExtra(PAGE_SWITCH_KEY, pageSwitchType.value());
    }

    // 进入页面时动画，在startActivity方法之后调用
    public static void enterPageAnim(Activity activity, PageSwitchType pageSwitchType) {
        if (PageSwitchType.PUSH_FROM_LEFT == pageSwitchType) { // 从左到右进入，从右到左返回
            pushLeftFromRight(activity);
        }
        else if (PageSwitchType.PUSH_FROM_RIGHT == pageSwitchType) { // 从右到左进入，从左到右返回
            pushRightFromLeft(activity);
        }
        else if (PageSwitchType.PUSH_LEFT_AND_RIGHT == pageSwitchType) {
            pushLeftAndRight(activity);
        }
        else if (PageSwitchType.FADE == pageSwitchType) { // 淡入淡出
            fade(activity);
        }
        else if (PageSwitchType.ZOOM == pageSwitchType) { // 放大缩小
            zoomScaleIn(activity);
        }
        else if (PageSwitchType.PUSH_UP_AND_DOWN == pageSwitchType) {
            pushUpAndDown(activity);
        }
        else if (PageSwitchType.PUSH_FROM_UP == pageSwitchType) {
            pushBottomFromUp(activity);
        }
        else if (PageSwitchType.PUSH_FROM_DOWN == pageSwitchType) {
            pushUpFromBottom(activity);
        }
        else if (PageSwitchType.SCALE_ROTATE_1 == pageSwitchType) {
            scaleRotateIn1(activity);
        }
        else if (PageSwitchType.SCALE_ROTATE_2 == pageSwitchType) {
            scaleRotateIn2(activity);
        }
        else if (PageSwitchType.SCALE_FROM_LEFT_TOP == pageSwitchType) {
            scaleFromLeftTop(activity);
        }
    }

    // 退出页面时动画，在finish方法之后调用
    public static void exitPageAnim(Activity activity) {
        PageSwitchType pageSwitchType = PageSwitchType.UNKNOWN;
        try {
            pageSwitchType = PageSwitchType.valueOf(activity.getIntent().getIntExtra(PAGE_SWITCH_KEY, 0));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (PageSwitchType.PUSH_FROM_LEFT == pageSwitchType) { // 从左到右进入，从右到左返回
            pushRightFromLeft(activity);
        }
        else if (PageSwitchType.PUSH_FROM_RIGHT == pageSwitchType) { // 从右到左进入，从左到右返回
            pushLeftFromRight(activity);
        }
        else if (PageSwitchType.PUSH_LEFT_AND_RIGHT == pageSwitchType) {
            pushLeftAndRight(activity);
        }
        else if (PageSwitchType.FADE == pageSwitchType) {
           fade(activity);
        }
        else if (PageSwitchType.ZOOM == pageSwitchType) {
            zoomScaleOut(activity);
        }
        else if (PageSwitchType.PUSH_UP_AND_DOWN == pageSwitchType) {
            pushUpAndDown(activity);
        }
        else if (PageSwitchType.PUSH_FROM_UP == pageSwitchType) {
            pushUpFromBottom(activity);
        }
        else if (PageSwitchType.PUSH_FROM_DOWN == pageSwitchType) {
            pushBottomFromUp(activity);
        }
        else if (PageSwitchType.SCALE_ROTATE_1 == pageSwitchType) {
            scaleRotateIn1(activity);
        }
        else if (PageSwitchType.SCALE_ROTATE_2 == pageSwitchType) {
            scaleRotateIn2(activity);
        }
        else if (PageSwitchType.SCALE_FROM_LEFT_TOP == pageSwitchType) {
            scaleFromLeftTop(activity);
        }
    }

    // ******************** 放大缩小效果 ************************************
    // 放大进入
    private static void zoomScaleIn(Activity activity) {
        pageSwitchAnim(activity, R.anim.zoom_scale_in, R.anim.zoom_scale_out);
    }

    // 缩小退出的效果
    private static void zoomScaleOut(Activity activity) {
        pageSwitchAnim(activity, R.anim.zoom_narrow_in, R.anim.zoom_narrow_out);
    }

    // ******************** 旋转效果 ************************************
    // 旋转进入
    private static void scaleRotateIn1(Activity activity) {
        pageSwitchAnim(activity, R.anim.scale_rotate_1, android.R.anim.fade_out);
    }

    // 旋转进入
    private static void scaleRotateIn2(Activity activity) {
        pageSwitchAnim(activity, R.anim.scale_rotate_2, android.R.anim.fade_out);
    }

    // *********************** 上下效果 ***************************
    // 下往上推出效果
    private static void pushUpFromBottom(Activity activity) {
        pageSwitchAnim(activity, R.anim.push_up_in, R.anim.push_up_out);
    }

    // 上往下推出效果
    private static void pushBottomFromUp(Activity activity) {
        pageSwitchAnim(activity, R.anim.push_down_in, R.anim.push_down_out);
    }

    // 上下交错的页面效果效果
    private static void pushUpAndDown(Activity activity) {
        pageSwitchAnim(activity, R.anim.slide_up_in, R.anim.slide_down_out);
    }

    // *********************** 左右效果 ***************************
    // 由右向左
    private static void pushLeftFromRight(Activity activity) {
        pageSwitchAnim(activity, R.anim.push_left_in, R.anim.push_left_out);
    }

    // 由左向右
    private static void pushRightFromLeft(Activity activity) {
        pageSwitchAnim(activity, R.anim.push_right_in, R.anim.push_right_out);
    }

    // 上下交错的页面效果效果
    private static void pushLeftAndRight(Activity activity) {
        pageSwitchAnim(activity, R.anim.slide_left_in, R.anim.slide_right_out);
    }

    // *********************** 角落出现 ***************************
    // 左上角展开淡入
    private static void scaleFromLeftTop(Activity activity) {
        pageSwitchAnim(activity, R.anim.scale_lefttop, android.R.anim.fade_out);
    }

    // 淡入淡出效果
    private static void fade(Activity activity) {
        pageSwitchAnim(activity, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static void pageSwitchAnim(Activity activity, int enterAnim, int exitAnim) {
        activity.overridePendingTransition(enterAnim,exitAnim);
    }
}
