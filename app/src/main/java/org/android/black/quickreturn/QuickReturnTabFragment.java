package org.android.black.quickreturn;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.android.black.R;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.Optional;
import widget.base.BaseResource;
import widget.base.data.RecycleViewAdapter;
import widget.base.ui.BaseFragment;
import widget.view.quickreturn.utils.QuickReturnService;

/**
 * Created by etiennelawlor on 6/23/14.
 */
public class QuickReturnTabFragment extends BaseFragment {

    @InjectView(R.id.rv)
    RecyclerView mRecyclerView;

    @Optional
    @InjectView(R.id.quick_return_footer_ll)
    View mQuickReturnFooterLinearLayout;

    @Optional
    @InjectView(R.id.quick_return_header_tv)
    View mQuickReturnHeaderTextView;

    // google plus
    @Optional
    @InjectView(R.id.quick_return_footer_tv)
    View quick_return_footer_tv;

    @Optional
    @InjectView(R.id.quick_return_footer_iv)
    View quick_return_footer_iv;

    QuickReturnType quickReturnType;

    public static QuickReturnTabFragment newInstance(QuickReturnType quickReturnType) {
        QuickReturnTabFragment fragment = new QuickReturnTabFragment();
        Bundle args = new Bundle();
        args.putString(QuickReturnType.QRT, quickReturnType.name());
        fragment.setArguments(args);
        return fragment;
    }

    public QuickReturnTabFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = this.getArguments();
        quickReturnType = QuickReturnType.valueOf(args.getString(QuickReturnType.QRT));
        if (QuickReturnType.GOOGLE == quickReturnType) {
            return super.initContentView(inflater, container, R.layout.fragment_quick_return_google_plus, this);
        } else {
            return super.initContentView(inflater, container, R.layout.fragment_quick_return_tab, this);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecycleViewAdapter adapter = new RecycleViewAdapter(getActivity());
        mRecyclerView.setHasFixedSize(true); //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        if (QuickReturnType.TWITTER == quickReturnType) {
            mQuickReturnHeaderTextView.setVisibility(View.GONE);
            QuickReturnInterface mCoordinator = (QuickReturnInterface) getActivity();
            QuickReturnService.quickReturnRecycleView(mRecyclerView,
                    mCoordinator.getTabs(), mQuickReturnFooterLinearLayout,
                    -BaseResource.resDimenPx(R.dimen.facebook_header_height),
                    BaseResource.resDimenPx(R.dimen.facebook_footer_height));
        } else if (QuickReturnType.FACEBOOK == quickReturnType) {
            QuickReturnService.quickReturnRecycleView(mRecyclerView,
                    mQuickReturnHeaderTextView, mQuickReturnFooterLinearLayout,
                    -BaseResource.resDimenPx(R.dimen.facebook_header_height),
                    BaseResource.resDimenPx(R.dimen.facebook_footer_height));
        } else if (QuickReturnType.GOOGLE == quickReturnType) {

            ArrayList<View> footerViews = new ArrayList<>();
            quick_return_footer_tv.setTag(R.id.scroll_threshold_key, 1);
            footerViews.add(quick_return_footer_tv);
            quick_return_footer_iv.setTag(R.id.scroll_threshold_key, 3);
            footerViews.add(quick_return_footer_iv);

            QuickReturnService.quickReturnGooglePlus(this.getActivity(), mRecyclerView, footerViews);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
