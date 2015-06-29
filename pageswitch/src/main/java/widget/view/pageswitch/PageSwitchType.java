package widget.view.pageswitch;

/**
 * Created by liumingkong on 15/6/24.
 */
public enum PageSwitchType {

    PUSH_FROM_LEFT(1),          // 从左到右推入
    PUSH_FROM_RIGHT(2),         // 从右到左推入
    PUSH_FROM_UP(6),            // 从上到下推入
    PUSH_FROM_DOWN(7),          // 从下到上推入
    FADE(3),                    // 淡入淡出
    ZOOM(4),                    // 放大缩小展示页面
    PUSH_UP_AND_DOWN(5),        // 上下交错的展示页面
    PUSH_LEFT_AND_RIGHT(11),    // 左右交错的展示页面
    SCALE_ROTATE_1(8),
    SCALE_ROTATE_2(9),
    SCALE_FROM_LEFT_TOP(10),
    UNKNOWN(0);

    private final int code;

    PageSwitchType(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }

    public static PageSwitchType valueOf(final int code) {
        for (PageSwitchType c : PageSwitchType.values()) {
            if (code == c.code) return c;
        }
        return UNKNOWN;
    }
}
