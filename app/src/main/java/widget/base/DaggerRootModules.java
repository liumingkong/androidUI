package widget.base;

import dagger.Module;

/**
 * Created by liumingkong on 15/6/18.
 *
 * Dagger规定 所有@Provides要放在一个@Module中，
 * 所以我们要么可以在一个Module中用includes参数把其他的Module类包含进来
 * 创建一个空的Module类，把所有的Module都包含到这个Module中来
 */
@Module (
        includes = {
                DaggerBasicModule.class,
        }
)
public class DaggerRootModules {
}
