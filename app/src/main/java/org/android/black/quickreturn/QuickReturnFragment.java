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
import widget.view.quickreturn.views.NotifyingScrollView;
import widget.view.quickreturn.views.QuickReturnScrollViewOnScrollChangedListener;

/**
 * Created by etiennelawlor on 6/23/14.
 */
public class QuickReturnFragment extends Fragment {

    // region Member Variables
    private QuickReturnViewType mQuickReturnViewType;

    @InjectView(R.id.scroll_view)
    NotifyingScrollView mNotifyingScrollView;
    @InjectView(R.id.quick_return_header_tv)
    TextView mQuickReturnHeaderTextView;
    @InjectView(R.id.quick_return_footer_tv)
    TextView mQuickReturnFooterTextView;
    // endregion

    //region Listeners
    //endregion

    // region Constructors
    public static QuickReturnFragment newInstance(Bundle extras) {
        QuickReturnFragment fragment = new QuickReturnFragment();
        fragment.setRetainInstance(true);
        fragment.setArguments(extras);
        return fragment;
    }

    public QuickReturnFragment() {
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
        View view = inflater.inflate(R.layout.fragment_quick_return, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int headerHeight = getResources().getDimensionPixelSize(R.dimen.facebook_header_height);
        int headerTranslation = -(headerHeight);

        int footerTranslation = getResources().getDimensionPixelSize(R.dimen.facebook_header_height);

        QuickReturnScrollViewOnScrollChangedListener scrollListener;
        
        switch (mQuickReturnViewType){
            case HEADER:
                mQuickReturnHeaderTextView.setVisibility(View.VISIBLE);
                scrollListener = new QuickReturnScrollViewOnScrollChangedListener.Builder(QuickReturnViewType.HEADER)
                        .header(mQuickReturnHeaderTextView)
                        .minHeaderTranslation(headerTranslation)
                        .build();
                mNotifyingScrollView.setOnScrollChangedListener(scrollListener);
                break;
            case FOOTER:
                mQuickReturnFooterTextView.setVisibility(View.VISIBLE);
                scrollListener = new QuickReturnScrollViewOnScrollChangedListener.Builder(QuickReturnViewType.FOOTER)
                        .footer(mQuickReturnFooterTextView)
                        .minFooterTranslation(footerTranslation)
                        .build();
                mNotifyingScrollView.setOnScrollChangedListener(scrollListener);
                break;
            case BOTH:
                mQuickReturnHeaderTextView.setVisibility(View.VISIBLE);
                mQuickReturnFooterTextView.setVisibility(View.VISIBLE);
                scrollListener = new QuickReturnScrollViewOnScrollChangedListener.Builder(QuickReturnViewType.BOTH)
                        .header(mQuickReturnHeaderTextView)
                        .minHeaderTranslation(headerTranslation)
                        .footer(mQuickReturnFooterTextView)
                        .minFooterTranslation(footerTranslation)
                        .build();
                mNotifyingScrollView.setOnScrollChangedListener(scrollListener);
                break;
        }

        mNotifyingScrollView.setOverScrollEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    // endregion
}

