package org.android.black.quickreturn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.android.black.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import widget.view.quickreturn.utils.QuickReturnViewType;
import widget.view.quickreturn.views.NotifyingWebView;
import widget.view.quickreturn.views.SpeedyQuickReturnWebViewOnScrollChangedListener;

/**
 * Created by etiennelawlor on 6/23/14.
 */
public class SpeedyQuickReturnWebViewFragment extends Fragment {

    // region Member Variables
    private QuickReturnViewType mQuickReturnViewType;

    @InjectView(R.id.web_view)
    NotifyingWebView mNotifyingWebView;
    @InjectView(R.id.quick_return_header_tv)
    TextView mQuickReturnHeaderTextView;
    @InjectView(R.id.quick_return_footer_tv)
    TextView mQuickReturnFooterTextView;
    // endregion

    //region Listeners
    //endregion

    // region Constructors
    public static SpeedyQuickReturnWebViewFragment newInstance(Bundle extras) {
        SpeedyQuickReturnWebViewFragment fragment = new SpeedyQuickReturnWebViewFragment();
        fragment.setRetainInstance(true);
        fragment.setArguments(extras);
        return fragment;
    }

    public SpeedyQuickReturnWebViewFragment() {
    }
    // endregion

    // region Lifecycle Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mQuickReturnViewType = QuickReturnViewType.valueOf(getArguments().getString("quick_return_view_type"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_return_webview, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNotifyingWebView.loadUrl("file:///android_asset/lipsum.html");
        SpeedyQuickReturnWebViewOnScrollChangedListener scrollListener;
        
        switch (mQuickReturnViewType){
            case HEADER:
                mQuickReturnHeaderTextView.setVisibility(View.VISIBLE);
                scrollListener = new SpeedyQuickReturnWebViewOnScrollChangedListener.Builder(getActivity(), QuickReturnViewType.HEADER)
                        .header(mQuickReturnHeaderTextView)
                        .build();
                mNotifyingWebView.setOnScrollChangedListener(scrollListener);
                break;
            case FOOTER:
                mQuickReturnFooterTextView.setVisibility(View.VISIBLE);
                scrollListener = new SpeedyQuickReturnWebViewOnScrollChangedListener.Builder(getActivity(), QuickReturnViewType.FOOTER)
                        .footer(mQuickReturnFooterTextView)
                        .build();
                mNotifyingWebView.setOnScrollChangedListener(scrollListener);
                break;
            case BOTH:
                mQuickReturnHeaderTextView.setVisibility(View.VISIBLE);
                mQuickReturnFooterTextView.setVisibility(View.VISIBLE);
                scrollListener = new SpeedyQuickReturnWebViewOnScrollChangedListener.Builder(getActivity(), QuickReturnViewType.BOTH)
                        .header(mQuickReturnHeaderTextView)
                        .footer(mQuickReturnFooterTextView)
                        .build();
                mNotifyingWebView.setOnScrollChangedListener(scrollListener);
                break;
        }

        mNotifyingWebView.setOverScrollEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    // endregion
}

