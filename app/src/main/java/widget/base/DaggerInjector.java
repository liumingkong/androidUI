package widget.base;

import dagger.ObjectGraph;

/**
 * Created by liumingkong on 15/6/19.
 */
public class DaggerInjector {

    private static ObjectGraph objectGraph = null;

    private static void init(final Object rootModule) {
        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(rootModule);
        } else {
            objectGraph = objectGraph.plus(rootModule);
        }
        objectGraph.injectStatics(); // Inject statics
    }

    // 初始化rootModule
    public static void init(final Object rootModule, final Object target) {
        init(rootModule);
        inject(target);
    }

    // 注入
    public static final void inject(final Object target) {
        objectGraph.inject(target);
    }
}
