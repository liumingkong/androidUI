package widget.base;

import java.util.concurrent.atomic.AtomicInteger;

import widget.view.pageswitch.PageSwitchType;

/**
 * Created by liumingkong on 15/6/19.
 */
public class BaseUtils {

    private static AtomicInteger pageCount = new AtomicInteger(0);
    public static String genPageTag(String name) {
        return name + pageCount.incrementAndGet();
    }

}
