package org.android.black.quickreturn;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.android.black.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import widget.view.quickreturn.utils.QuickReturnAnimationType;
import widget.view.quickreturn.utils.QuickReturnViewType;
import widget.view.quickreturn.views.QuickReturnListViewOnScrollListener;
import widget.view.quickreturn.views.SpeedyQuickReturnListViewOnScrollListener;

/**
 * Created by etiennelawlor on 6/23/14.
 */
public class QuickReturnHeaderListViewFragment extends ListFragment {

    // region Member Variables
    private String[] mValues;
    private QuickReturnAnimationType mQuickReturnAnimationType;

    protected QuickReturnListViewOnScrollListener mScrollListener;

    @InjectView(android.R.id.list)
    ListView mListView;
    @InjectView(R.id.quick_return_tv)
    TextView mQuickReturnTextView;
    // endregion

    // region Constructors
    public static QuickReturnHeaderListViewFragment newInstance(Bundle extras) {
        QuickReturnHeaderListViewFragment fragment = new QuickReturnHeaderListViewFragment();
        fragment.setRetainInstance(true);
        fragment.setArguments(extras);
        return fragment;
    }
    
    public static QuickReturnHeaderListViewFragment newInstance() {
        QuickReturnHeaderListViewFragment fragment = new QuickReturnHeaderListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public QuickReturnHeaderListViewFragment() {
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mQuickReturnAnimationType = QuickReturnAnimationType.valueOf(getArguments().getString("quick_return_animation_type"));
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_quick_return_header, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> datas = new ArrayList<>();
        for (int i = 0 ; i< 20; i ++) {
            datas.add("dfjlksfj" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item, R.id.item_tv, datas);

        mListView.setAdapter(adapter);

        int headerHeight = getActivity().getResources().getDimensionPixelSize(R.dimen.facebook_header_height);

        SpeedyQuickReturnListViewOnScrollListener scrollListener2;

        switch (mQuickReturnAnimationType){
            case TRANSLATION_SIMPLE:
                mScrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.HEADER)
                        .header(mQuickReturnTextView)
                        .minHeaderTranslation(-headerHeight)
                        .build();
                mListView.setOnScrollListener(mScrollListener);
                break;
            case TRANSLATION_SNAP:
                mScrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.HEADER)
                        .header(mQuickReturnTextView)
                        .minHeaderTranslation(-headerHeight)
                        .isSnappable(true)
                        .build();
                mListView.setOnScrollListener(mScrollListener);
                break;
            case TRANSLATION_ANTICIPATE_OVERSHOOT:
                scrollListener2 = new SpeedyQuickReturnListViewOnScrollListener.Builder(getActivity(), QuickReturnViewType.HEADER)
                        .header(mQuickReturnTextView)
                        .build();
                mListView.setOnScrollListener(scrollListener2);
                break;
            default:
                mScrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.HEADER)
                        .header(mQuickReturnTextView)
                        .minHeaderTranslation(-headerHeight)
                        .build();
                mListView.setOnScrollListener(mScrollListener);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    // endregion
}
