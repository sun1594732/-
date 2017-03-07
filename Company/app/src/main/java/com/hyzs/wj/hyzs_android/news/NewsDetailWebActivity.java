package com.hyzs.wj.hyzs_android.news;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;


import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.util.DensityUtils;
import com.hyzs.wj.hyzs_android.widget.ProgressDialog;
import com.hyzs.wj.hyzs_android.widget.TitleBar;

import butterknife.BindView;

public class NewsDetailWebActivity extends BaseActivity implements View.OnClickListener {


	@BindView(R.id.tl_news_detail)
	TitleBar mTitleBar;
	@BindView(R.id.wv_news_detail)
	WebView wv_news_detail;
	private Dialog mProgressLoading;
	private String mUrl;
	private String substring;//id
	private String title;//分享标题
	private String img;//分享图片的路径
	
	@Override
	protected void initListener() {
		ImageView share = new ImageView(this);
		share.setImageResource(R.mipmap.ic_launcher);
		share.setPadding(DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14));
		mTitleBar.setRightView(share);
		share.setOnClickListener(this);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_news_detail_web;
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		img = intent.getStringExtra("img");
		int type = intent.getIntExtra("type", 2);
		int id = intent.getIntExtra("id", 0);
		if (type == 0) {
//			mUrl = intent.getStringExtra("url");
		} else if (type == 1) {
//			mUrl = String.format(Api.INDEX.NEWS_DETAIL, id);
		} else if(type == 2){//社区新闻详情
//			mUrl = String.format(Api.BBS.BBS_NEWS_DETAIL , id);
		}
		int indexOf = mUrl.indexOf("=");
		substring = mUrl.substring(indexOf+1);
		mProgressLoading = ProgressDialog.createProgressLoading(this);
		mProgressLoading.show();
		mTitleBar.setTitleText("新闻详情");

//		LogUtils.e(mUrl);
		WebSettings webSettings = wv_news_detail.getSettings();

		webSettings.setSupportZoom(true);
		webSettings.setTextSize(WebSettings.TextSize.LARGER);

		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);
		// 设置支持缩放
		webSettings.setBuiltInZoomControls(true);
		wv_news_detail.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100) {
					mProgressLoading.dismiss();
				}
			}
		});
		wv_news_detail.loadUrl(mUrl);
	}

	/**
	 * .
	 * 点击分享按钮回调
	 *
	 * @param v 分享按钮
	 */
	@Override
	public void onClick(View v) {
//		PopWindowUtils show = new PopWindowUtils(NewsDetailWebActivity.this,substring,"1",title,img,mUrl);
//		show.shareAndReport(R.id.tl_news_detail);
	}
}
