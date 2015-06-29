package widget.base.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by liumingkong on 15/6/24.
 */
public class BaseFragment extends Fragment {

    private String pageTag;

    @Inject
    public Bus bus;

    @Optional
    @InjectView(R.id.common_header_left_btn_rlv)
    View common_header_left_btn_rlv;

    @Optional
    @InjectView(R.id.common_header_title_tv)
    TextView common_header_title_tv;

    // 初始化
    protected View initContentView(LayoutInflater inflater, ViewGroup container,
                                   int layoutId, Fragment fragment) {
        View view = inflater.inflate(layoutId, container, false);
        ButterKnife.inject(fragment, view);
        return view;
    }

    // 设置返回键生效
    protected void setBackBtn(final Fragment fragment) {
        if (!Utils.isNull(common_header_left_btn_rlv)) {
            common_header_left_btn_rlv.setVisibility(View.VISIBLE);
            common_header_left_btn_rlv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.getActivity().finish();
                }
            });
        }
    }

    // 采用懒加载的方式，标识当前页面
    protected String getPageTag() {
        if (Utils.isNull(this.pageTag)) {
            this.pageTag = BaseUtils.genPageTag(this.getClass().getName());
        }
        return this.pageTag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DaggerInjector.inject(this);
        bus.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bus.unregister(this);
    }
}
