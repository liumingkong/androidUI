package widget.base;

import android.content.res.Resources;

/**
 * Created by liumingkong on 15/6/24.
 */
public class BaseResource {

    public static String resString(int strId) {
        return getResources().getString(strId);
    }

    private static Resources getResources() {
        return BaseApplication.getAppContext().getResources();
    }

    public static int resColor(int colorId) {
        return getResources().getColor(colorId);
    }

    public static float resDimen(int dimenId) {
        return getResources().getDimension(dimenId);
    }

    public static int resDimenPx(int dimenId) {
        return getResources().getDimensionPixelSize(dimenId);
    }

}
