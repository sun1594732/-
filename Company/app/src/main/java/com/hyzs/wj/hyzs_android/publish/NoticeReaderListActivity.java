package com.hyzs.wj.hyzs_android.publish;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.hyzs.wj.hyzs_android.MyApplication;
import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.hyzs.wj.hyzs_android.http.response.JsonResponseHandler;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class NoticeReaderListActivity extends BaseActivity implements View.OnClickListener {

	@BindView(R.id.iv_browse_back)
	ImageView mBack;
	@BindView(R.id.menu_img)
	TextView mMenuImg;
	@BindView(R.id.menu_tv)
	ImageView mMenuTv;
//	@BindView(item_header_search)
//	EditText mItemHeaderSearch;
	@BindView(R.id.browse_list)
	ListView mBrowseList;
	private String noteid;
	/**
	 * type --0没有查看，1已查看，2督促查看，-1显示全部
	 */
	private int type = 0;
//	private List<BbsMemberBean.DataBean> members;
//	private BrowseStaffAdapter adapter;
	private TextView tv_nearby_menu_yes, tv_nearby_menu_no;
	private String str_search = "";

	@Override
	protected int getLayoutId() {
		menuView = View.inflate(this, R.layout.fragment_whosee_menu, null );
		tv_nearby_menu_yes = (TextView) menuView.findViewById(R.id.tv_nearby_menu_yes);
		tv_nearby_menu_no = (TextView) menuView.findViewById(R.id.tv_nearby_menu_no);
		tv_nearby_menu_yes.setOnClickListener(this);
		tv_nearby_menu_no.setOnClickListener(this);
		return R.layout.activity_notice_reader_list;
	}

	@Override
	protected void initData() {
		noteid = getIntent().getExtras().getString("noteid") + "";
//		mItemHeaderSearch.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//			}
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//				str_search = mItemHeaderSearch.getText().toString();
//				if (str_search.length() != 0) {
//					Toast.makeText(getApplicationContext(), str_search, Toast.LENGTH_SHORT).show();
//					initDataSearch(str_search);
//				} else if (str_search.length() == 0) {
//					initDataSearch("");
//				}
//			}
//		});
//		initDataSearch("");
	}

	@OnClick({R.id.iv_browse_back, R.id.menu_img, R.id.menu_tv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.iv_browse_back:
				finish();
				break;
//			case menu_img:
			case R.id.menu_tv:
				makePopupWindow(view);
				break;
			case R.id.tv_nearby_menu_yes:
				mMenuImg.setText("已查看");
				type = 1;
				window.dismiss();
//				initDataSearch(str_search);
				break;
			case R.id.tv_nearby_menu_no:
				mMenuImg.setText("未查看");
				type = 0;
				window.dismiss();
//				initDataSearch(str_search);
				break;
		}
	}
//	private void initDataSearch(String str_search) {
//		// // 通知被查看情况_带查询 Newid --通知id type --0没有查看，1已查看，2督促查看，-1显示全部
//		// search--查询人名称
//		String url = Api.BBS.BBS_NOTICE_SEARCH + "?Newid=" + noteid + "&type=" + type + "&search=" + str_search
//				+ "&psize=100" + "&pindex=1";
//		Log.e("TAG", "initDataSearch==" + url);
//		HttpUtils.get(MyApplication.getContext(), url, null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				BbsMemberBean bean = new Gson().fromJson(response, BbsMemberBean.class);
//				members = bean.getData();
//				adapter = new BrowseStaffAdapter(NoticeReaderListActivity.this, members);
//				mBrowseList.setAdapter(adapter);
//			}
//		});
//	}
	private View menuView;
	private PopupWindow window;
	private void makePopupWindow(View v) {
		window = new PopupWindow(menuView, 366, ViewGroup.LayoutParams.WRAP_CONTENT);
		// 设置PopupWindow外部区域是否可触摸
		window.setOutsideTouchable(true);
		window.setBackgroundDrawable(new BitmapDrawable());
		window.setFocusable(true); // 设置PopupWindow可获得焦点
		window.setTouchable(true); // 设置PopupWindow可触摸
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			window.showAsDropDown(v, -33, 1, Gravity.RIGHT);
		} else {
			window.showAsDropDown(v);
		}
	}

}
