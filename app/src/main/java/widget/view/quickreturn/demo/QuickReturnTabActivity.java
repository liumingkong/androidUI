package widget.view.quickreturn.demo;

import widget.base.ui.BaseActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.black.common.ui.UIUtils;

import org.android.black.R;

import butterknife.InjectView;
import widget.base.BaseResource;
import widget.view.pageslidingtab.PagerSlidingTabStrip;
import widget.view.quickreturn.utils.QuickReturnAnimationType;
import widget.view.quickreturn.utils.QuickReturnViewType;

//
///**
// * Created by liumingkong on 15/6/24.
// */
public class QuickReturnTabActivity extends BaseActivity implements QuickReturnInterface {

    private FragmentPagerAdapter mSectionsPagerAdapter;
    private LinearLayout mTabsLinearLayout;

    @InjectView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @InjectView(R.id.pagers)
    ViewPager mViewPager;

    QuickReturnType quickReturnType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quickReturnType = QuickReturnType.valueOf(getIntent().getStringExtra(QuickReturnType.QRT));
        if (QuickReturnType.TWITTER == quickReturnType) {
            setContentView(R.layout.activity_quick_return_twitter);
        } else {
            setContentView(R.layout.activity_quick_return_facebook);
        }

        setBackBtn();
        if (QuickReturnType.TWITTER == quickReturnType || QuickReturnType.FACEBOOK == quickReturnType) {
            mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
        } else if (QuickReturnType.LISTVIEW == quickReturnType) {
            mSectionsPagerAdapter = new ListSectionsPagerAdapter(this.getSupportFragmentManager());
        } else if (QuickReturnType.WEBVIEW == quickReturnType) {
            mSectionsPagerAdapter = new WebViewSectionsPagerAdapter(this.getSupportFragmentManager());
        } else if (QuickReturnType.SCROLLVIEW == quickReturnType) {
            mSectionsPagerAdapter = new ScrollSectionsPagerAdapter(this.getSupportFragmentManager());
        }

        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabs.setAllCaps(false);        // 是否设为全部大写
        mTabs.setShouldExpand(true);    // 是否

        mTabs.setIndicatorColorResource(android.R.color.holo_blue_dark); // 滑动条的颜色
        mTabs.setDividerColor(BaseResource.resColor(android.R.color.transparent));              // 每个标签的分割线的颜色
        mTabs.setIndicatorHeight(UIUtils.dp2px(this, 6)); // 滑动条的高度
        mTabs.setUnderlineHeight(UIUtils.dp2px(this, 0)); // 滑动条所在的那个全宽线的高度
        if (QuickReturnType.TWITTER == quickReturnType || QuickReturnType.FACEBOOK == quickReturnType) {
            mTabs.setOnPageChangeListener(mTabsOnPageChangeListener);
        } else {
            mTabs.setOnPageChangeListener(mTabsStringOnPageChangeListener);
        }
        mTabs.setViewPager(mViewPager);

        // Set first tab selected
        mTabsLinearLayout = ((LinearLayout) mTabs.getChildAt(0));
        try {
            ImageButton ib = (ImageButton) mTabsLinearLayout.getChildAt(0);
            ib.setImageResource(R.drawable.ic_action_news_highlighted);
        } catch (Throwable throwable) {

        }
    }
    // region Listeners
    private ViewPager.OnPageChangeListener mTabsStringOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d("QuickReturnScrollViewActivity", "onPageScrolled() : position - "+position);

        }

        @Override
        public void onPageSelected(int position) {
//            Log.d("QuickReturnScrollViewActivity", "onPageSelected() : position - "+position);

            for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
                TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
                if(i == position){
                    tv.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                } else {
                    tv.setTextColor(getResources().getColor(android.R.color.darker_gray));
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ViewPager.OnPageChangeListener mTabsOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
                ImageButton ib = (ImageButton) mTabsLinearLayout.getChildAt(i);
                switch (i) {
                    case 0:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_news_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_news);
                        break;
                    case 1:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_users_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_users);
                        break;
                    case 2:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_messages_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_messages);
                        break;
                    case 3:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_notifications_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_notifications);
                        break;
                    case 4:
                        if (i == position)
                            ib.setImageResource(R.drawable.ic_action_more_highlighted);
                        else
                            ib.setImageResource(R.drawable.ic_action_more);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public PagerSlidingTabStrip getTabs() {
        return mTabs;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return QuickReturnTabFragment.newInstance(quickReturnType);
        }

        @Override
        public int getPageIconResId(int position) {
            return R.drawable.ic_action_more;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    public class ListSectionsPagerAdapter extends FragmentPagerAdapter {

        public ListSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_SIMPLE.name());
                    return QuickReturnHeaderListViewFragment.newInstance(bundle);
                case 1:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_SNAP.name());
                    return QuickReturnHeaderListViewFragment.newInstance(bundle);
                case 2:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_ANTICIPATE_OVERSHOOT.name());
                    return QuickReturnHeaderListViewFragment.newInstance(bundle);
                case 3:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_SIMPLE.name());
                    return QuickReturnFooterListViewFragment.newInstance(bundle);
                case 4:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_SNAP.name());
                    return QuickReturnFooterListViewFragment.newInstance(bundle);
                case 5:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_ANTICIPATE_OVERSHOOT.name());
                    return QuickReturnFooterListViewFragment.newInstance(bundle);
                case 6:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_SNAP.name());
                    return QuickReturnWithExtraOnScrollListenerFragment.newInstance(bundle);
                default:
                    bundle.putString("quick_return_animation_type",
                            QuickReturnAnimationType.TRANSLATION_SIMPLE.name());
                    return QuickReturnHeaderListViewFragment.newInstance(bundle);
            }
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "QRHeaderSimple";
                case 1:
                    return "QRHeaderSnap";
                case 2:
                    return "QRHeaderAnticipateOvershoot";
                case 3:
                    return "QRFooterSimple";
                case 4:
                    return "QRFooterSnap";
                case 5:
                    return "QRFooterAnticipateOvershoot";
                case 6:
                    return "WithExtraOnScrollListener";
            }
            return null;
        }

    }

    public class WebViewSectionsPagerAdapter extends FragmentPagerAdapter {

        public WebViewSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.HEADER.name());
                    return QuickReturnWebViewFragment.newInstance(bundle);
                case 1:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.HEADER.name());
                    return SpeedyQuickReturnWebViewFragment.newInstance(bundle);
                case 2:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.FOOTER.name());
                    return QuickReturnWebViewFragment.newInstance(bundle);
                case 3:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.FOOTER.name());
                    return SpeedyQuickReturnWebViewFragment.newInstance(bundle);
                case 4:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.BOTH.name());
                    return QuickReturnWebViewFragment.newInstance(bundle);
                case 5:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.BOTH.name());
                    return SpeedyQuickReturnWebViewFragment.newInstance(bundle);
                default:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.HEADER.name());
                    return SpeedyQuickReturnWebViewFragment.newInstance(bundle);
            }
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "QRHeader";
                case 1:
                    return "SpeedyQRHeader";
                case 2:
                    return "QRFooter";
                case 3:
                    return "SpeedyQRFooter";
                case 4:
                    return "QRBoth";
                case 5:
                    return "SpeedyQRBoth";

            }
            return null;
        }
    }

    public class ScrollSectionsPagerAdapter extends FragmentPagerAdapter {

        public ScrollSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.HEADER.name());
                    return QuickReturnFragment.newInstance(bundle);
                case 1:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.HEADER.name());
                    return SpeedyQuickReturnFragment.newInstance(bundle);
                case 2:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.FOOTER.name());
                    return QuickReturnFragment.newInstance(bundle);
                case 3:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.FOOTER.name());
                    return SpeedyQuickReturnFragment.newInstance(bundle);
                case 4:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.BOTH.name());
                    return QuickReturnFragment.newInstance(bundle);
                case 5:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.BOTH.name());
                    return SpeedyQuickReturnFragment.newInstance(bundle);
                default:
                    bundle.putString("quick_return_view_type",
                            QuickReturnViewType.HEADER.name());
                    return SpeedyQuickReturnFragment.newInstance(bundle);
            }
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "QRHeader";
                case 1:
                    return "SpeedyQRHeader";
                case 2:
                    return "QRFooter";
                case 3:
                    return "SpeedyQRFooter";
                case 4:
                    return "QRBoth";
                case 5:
                    return "SpeedyQRBoth";

            }
            return null;
        }
    }
}