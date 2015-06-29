package widget.view.quickreturn.demo;

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
public class QuickReturnFooterListViewFragment extends ListFragment {

    // region Member Variables
    private String[] mValues;
    private QuickReturnAnimationType mQuickReturnAnimationType;

    @InjectView(android.R.id.list)
    ListView mListView;
    @InjectView(R.id.quick_return_tv)
    TextView mQuickReturnTextView;
    // endregion

    // region Constructors
    public static QuickReturnFooterListViewFragment newInstance(Bundle extras) {
        QuickReturnFooterListViewFragment fragment = new QuickReturnFooterListViewFragment();
        fragment.setRetainInstance(true);
        fragment.setArguments(extras);
        return fragment;
    }
    
    public static QuickReturnFooterListViewFragment newInstance() {
        QuickReturnFooterListViewFragment fragment = new QuickReturnFooterListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public QuickReturnFooterListViewFragment() {
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
        View view = inflater.inflate(R.layout.fragment_listview_quick_return_footer, container, false);
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

        int footerHeight = getActivity().getResources().getDimensionPixelSize(R.dimen.facebook_header_height);

        QuickReturnListViewOnScrollListener scrollListener;
        SpeedyQuickReturnListViewOnScrollListener scrollListener2;

        switch (mQuickReturnAnimationType){
            case TRANSLATION_SIMPLE:
                scrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.FOOTER)
                        .footer(mQuickReturnTextView)
                        .minFooterTranslation(footerHeight)                   
                        .build();
                mListView.setOnScrollListener(scrollListener);
                break;
            case TRANSLATION_SNAP:
                scrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.FOOTER)
                        .footer(mQuickReturnTextView)
                        .minFooterTranslation(footerHeight)
                        .isSnappable(true)
                        .build();
                mListView.setOnScrollListener(scrollListener);
                break;
            case TRANSLATION_ANTICIPATE_OVERSHOOT:
                scrollListener2 = new SpeedyQuickReturnListViewOnScrollListener.Builder(getActivity(), QuickReturnViewType.FOOTER)
                        .footer(mQuickReturnTextView)
                        .build();
                mListView.setOnScrollListener(scrollListener2);
                break;
            default:
                scrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.FOOTER)
                        .footer(mQuickReturnTextView)
                        .minFooterTranslation(footerHeight)
                        .build();
                mListView.setOnScrollListener(scrollListener);
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
