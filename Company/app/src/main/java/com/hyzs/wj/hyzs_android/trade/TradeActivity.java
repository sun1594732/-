package com.hyzs.wj.hyzs_android.trade;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;


import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.util.StatusBarUtil;
import com.hyzs.wj.hyzs_android.widget.scrollablelayout.ScrollableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class TradeActivity extends BaseActivity {

    @BindView(R.id.tab_title)
    TabLayout tab_title;
    @BindView(R.id.vp_content)
    ViewPager vp_content;
    @BindView(R.id.scrollable_layout)
    ScrollableLayout scrollable_layout;
    @BindView(R.id.iv_trade_mine)
    ImageView img_my_trade;
    @BindView(R.id.back)
    ImageView mBack;


    private List<Fragment> pagers;
    public List<String> titles;
//    private List<NewsTypeBean.DataBean> mTypeBean;
//    private List<BannerBean.DataBean> mData;
    private String bbsid;

    @Override
    protected int getLayoutId() {
        bbsid = getIntent().getStringExtra("bbsid") + "";
        return R.layout.activity_trade;
    }

//    private void getTypes() {
//
//        HttpUtils.get(MyApplication.getContext(), Api.BBS.GET_TADE_TYPE_ALL+"?stype=0", null, new JsonResponseHandler() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onSuccess(String content, int id) {
//                if (JsonUtils.isSuccess(content)) {
//                    NewsTypeBean typeBean = new Gson().fromJson(content, NewsTypeBean.class);
//                    mTypeBean = typeBean.getData();
//                    pagers = new ArrayList<>();
//                    titles = new ArrayList<>();
//                    titles.add("全部");
//                    pagers.add(TradeListFragments.newInstance(0, bbsid));
//                    for (int i = 0; i < mTypeBean.size(); i++) {
//                        pagers.add(TradeListFragments.newInstance(mTypeBean.get(i).getId(), bbsid));
//                        titles.add(mTypeBean.get(i).getName());
//                    }
//                    vp_content.setAdapter(new NewsFragmentAdapter(TradeActivity.this.getSupportFragmentManager(), pagers, titles));
//                    vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                        @Override
//                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                        }
//
//                        @Override
//                        public void onPageSelected(int position) {
//                            scrollable_layout.getHelper().setCurrentScrollableContainer((ScrollableHelper.ScrollableContainer) pagers.get(position));
//                        }
//
//                        @Override
//                        public void onPageScrollStateChanged(int state) {
//
//                        }
//                    });
//                    tab_title.setTabMode(TabLayout.MODE_SCROLLABLE);
//                    tab_title.setupWithViewPager(vp_content);
//                    vp_content.setCurrentItem(0);
//                } else {
//                    ToastUtils.showShort(TradeActivity.this, "Error:" + JsonUtils.getErrorMessage(content));
//                }
//            }
//        });
//    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.StatusBarDarkMode(this, StatusBarUtil.statusBarLightMode(this));
//            StatusBarUtil.setStatusBarColor(this, R.color.green);
        }
//        getTypes();
    }

    @OnClick({R.id.back, R.id.iv_trade_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_trade_mine:
                Intent intent = new Intent(this, MySellActivity.class);
                startActivity(intent);
                break;
        }
    }

}
