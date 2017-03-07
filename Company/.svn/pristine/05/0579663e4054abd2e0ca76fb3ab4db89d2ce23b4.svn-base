package com.hyzs.wj.hyzs_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.news.BbsNewsActivity;
import com.hyzs.wj.hyzs_android.publish.NoticeDetailsActivity;
import com.hyzs.wj.hyzs_android.publish.NoticeReaderListActivity;
import com.hyzs.wj.hyzs_android.trade.TradeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.help)
    Button mHelp;
    @BindView(R.id.trade)
    Button mTrade;
    @BindView(R.id.publish)
    Button mPublish;
    @BindView(R.id.question)
    Button mQuestion;
    @BindView(R.id.news)
    Button mNews;
    @BindView(R.id.interestCircle)
    Button mInterestCircle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @OnClick({R.id.help, R.id.trade, R.id.publish, R.id.question, R.id.news, R.id.interestCircle})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {

            case R.id.help:

                break;
            case R.id.trade:
                intent = new Intent(this, TradeActivity.class);
                break;
            case R.id.publish:
                intent = new Intent(this, NoticeDetailsActivity.class);
                break;
            case R.id.question:
                break;
            case R.id.news:
                intent = new Intent(this, BbsNewsActivity.class);
                break;
            case R.id.interestCircle:
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
