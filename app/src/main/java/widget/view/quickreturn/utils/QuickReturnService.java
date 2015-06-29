package widget.view.quickreturn.utils;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;

import org.android.black.R;

import java.util.ArrayList;

import widget.view.quickreturn.views.QuickReturnRecyclerViewOnScrollListener;
import widget.view.quickreturn.views.SpeedyQuickReturnRecyclerViewOnScrollListener;

/**
 * Created by liumingkong on 15/6/25.
 */
public class QuickReturnService {

    // 快速返回，适用于RecycleView
    public static void quickReturnRecycleView(RecyclerView recyclerView,
                                              View headerView, View footerView,
                                              int headerTranslatePx, int footerTranslatePx) {
        QuickReturnRecyclerViewOnScrollListener scrollListener = new QuickReturnRecyclerViewOnScrollListener.Builder(QuickReturnViewType.BOTH)
                .header(headerView)
                .minHeaderTranslation(headerTranslatePx)
                .footer(footerView)
                .minFooterTranslation(footerTranslatePx)
                .isSnappable(true)
                .build();
        recyclerView.setOnScrollListener(scrollListener);
    }

    public static void quickReturnGooglePlus(Activity activity, RecyclerView recyclerView, ArrayList footerViews) {
        SpeedyQuickReturnRecyclerViewOnScrollListener scrollListener = new SpeedyQuickReturnRecyclerViewOnScrollListener.Builder(activity,
                QuickReturnViewType.GOOGLE_PLUS)
                .footerViews(footerViews)
                .build();
        recyclerView.setOnScrollListener(scrollListener);
    }
}
