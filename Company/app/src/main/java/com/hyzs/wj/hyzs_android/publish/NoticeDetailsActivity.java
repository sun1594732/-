package com.hyzs.wj.hyzs_android.publish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;


import com.hyzs.wj.hyzs_android.MyApplication;
import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.hyzs.wj.hyzs_android.http.response.JsonResponseHandler;

import okhttp3.Call;


public class NoticeDetailsActivity extends Activity {

	private String noteID;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice_detail);
		//		ButterKnife.bind(this);
		initView();
		initData();
	}

	private void initView() {
		mTvNoticeSee = (TextView) findViewById(R.id.tv_notice_see);
		mTvNoticeTitle = (TextView) findViewById(R.id.tv_notice_title);
		mBtnNext = (TextView) findViewById(R.id.btn_next);
		mTvNoticeInfo = (TextView) findViewById(R.id.tv_notice_info);
		mItemNoticeDate = (TextView) findViewById(R.id.item_notice_date);
	}


	private void initData() {
//		if ((Boolean) SPUtils.get(MyApplication.getContext(), "isadmin", false)) {
//			mBtnNext.setVisibility(View.VISIBLE);
//		} else {
//			mBtnNext.setVisibility(View.GONE);
//		}
//		String see = (String) getIntent().getExtras().get("see");
//		String info = (String) getIntent().getExtras().get("info");
//		String date = (String) getIntent().getExtras().get("date");
//		String title = (String) getIntent().getExtras().get("title");
//		noteID = getIntent().getExtras().get("noteid") + "";
		mTvNoticeSee.setText("test");
		mTvNoticeTitle.setText("test");
		mTvNoticeInfo.setText(Html.fromHtml("test"));
		mItemNoticeDate.setText("test");

		mTvNoticeTitle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击查看阅读详情
				Intent intent = new Intent(NoticeDetailsActivity.this, NoticeReaderListActivity.class);
				intent.putExtra("noteid", noteID);
				startActivity(intent);
			}
		});
		// 上传自己阅读过的信息
//		yesIsee(noteID);
	}

	private TextView mTvNoticeSee, mTvNoticeTitle, mBtnNext, mTvNoticeInfo, mItemNoticeDate;

	/**
	 * 上传朕已阅
	 */
//	protected void yesIsee(String noteid) {
//		final String url = Api.BBS.YESISEE + "?noteid=" + noteid + "&uid=" + SPUtils.get(MyApplication.getContext(), "userid", "");
//		HttpUtils.get(MyApplication.getContext(), url, null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				LogUtils.e(e.toString() + "上传朕已阅" + "url=" + url);
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				ToastUtils.showShort(NoticeDetailsActivity.this, "您已阅读");
//			}
//		});
//	}

}
