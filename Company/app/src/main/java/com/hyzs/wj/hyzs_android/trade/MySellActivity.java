package com.hyzs.wj.hyzs_android.trade;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.hyzs.wj.hyzs_android.MyApplication;
import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.hyzs.wj.hyzs_android.http.response.JsonResponseHandler;
import com.hyzs.wj.hyzs_android.trade.TradeMyActivity;
import com.hyzs.wj.hyzs_android.util.DensityUtils;
import com.hyzs.wj.hyzs_android.widget.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/14.
 */

/**
 * 我的售卖
 */
public class MySellActivity extends BaseActivity {
	@BindView(R.id.tl_news_detail)
	TitleBar mTitleBar;
	@BindView(R.id.sl_my_sell_content)
	SwipeRefreshLayout mRefreshLayout;
	@BindView(R.id.rv_my_sell_content)
	RecyclerView mRecyclerVertical;
	@BindView(R.id.tv_my_sell_emply)
	TextView mTextViewEmply;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mProgressLoading.dismiss();
		}
	};
	private TextView released;
//	private List<BbsMySellBean.DataEntry> mData;
	private LinearLayoutManager mLayoutManager;
//	private BbsMySellAdapter mAdapter;
	private int mCurrentPage = 1;
	private Dialog mProgressLoading;
	static final private int GET_CODE = 0;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_my_sell;
	}

	@Override
	protected void initData() {
		released = new TextView(this);

			released.setText("发布");
			released.setTextColor(Color.parseColor("#1ccd9b"));
			released.setTextSize(14);
		released.setPadding(DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14));
		mTitleBar.setRightView(released);

		mTitleBar.setTitleText("我的售卖");

		mProgressLoading = com.hyzs.wj.hyzs_android.widget.ProgressDialog.createProgressLoading(this);
		mProgressLoading.show();
		handler.sendEmptyMessageDelayed(1,2000);
//		setData();
	}

//	private void setData() {
//		mData = new ArrayList<>();
//		//请求数据
//		getNewsListData(1);
//		mAdapter = new BbsMySellAdapter(MySellActivity.this, mData);
//		mLayoutManager = new LinearLayoutManager(MySellActivity.this, LinearLayoutManager.VERTICAL, false);
//		mRecyclerVertical.setLayoutManager(mLayoutManager);
//		mRecyclerVertical.setAdapter(mAdapter);
//	}

	@Override
	protected void initListener() {
		super.initListener();
		//发布售卖
		released.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//没有加入社区的就不能发布
//				if (!"-1".equals(SPUtils.get(MyApplication.getContext(), "bbsid", ""))) {

				Intent intent = new Intent(MySellActivity.this, TradeMyActivity.class);
				startActivity(intent);
//			else{
//					ToastUtils.showShort(MySellActivity.this,"您还没加入社区");
//				}
//			}
		}});
//
//		mAdapter.setOnItemClickListener(this);
//
//		//上拉加载
//		mRecyclerVertical.addOnScrollListener(new RecyclerView.OnScrollListener() {
//			@Override
//			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//				super.onScrollStateChanged(recyclerView, newState);
//
//				//判断是否是停止滑动并且是滑到了最后一个item
//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//					// 判断最后一个条目是否出现了
//					//mLayoutManager：LinearLayoutManager 布局管理器的对象
//					int last = mLayoutManager.findLastVisibleItemPosition();
//					if (last == mData.size() - 1) {
//						//如果出现了 加载下一页数据
//						mRefreshLayout.setRefreshing(true);
////						getNewsListData(mCurrentPage);
//					}
//				}
//			}
//
//			@Override
//			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
//			}
//		});
//		//下拉刷新
//		// 设置SwipeRefreshLayout 的监听和加载框的颜色
//		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//			@Override
//			public void onRefresh() {
//				mCurrentPage = 1;
////				getNewsListData(1);
//			}
//		});
//		// 设置SwipeRefreshLayout 加载框的颜色
//		mRefreshLayout.setColorSchemeColors
//				(Color.parseColor("#1CCD9B"), Color.parseColor("#1CCD9B"), Color.parseColor("#1CCD9B"), Color.parseColor("#1CCD9B"));
//	}

//	private void getNewsListData(final int position) {
//		String userid = SPUtils.get(this, "userid", "").toString();
//
//		String path = String.format(Api.BBS.BBS_MY_SELL_PATH, userid, position);
//		Log.e("url==", path + "&stype=0");
//		HttpUtils.get(this, path + "&stype=0", null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				if (mProgressLoading != null) {
//					mProgressLoading.dismiss();
//				}
//				ToastUtils.showShort(MySellActivity.this, "网络请求错误:" + e.getMessage());
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				if (mProgressLoading != null) {
//					mProgressLoading.dismiss();
//				}
//				if (JsonUtils.isSuccess(response)) {
//					BbsMySellBean bean = new Gson().fromJson(response, BbsMySellBean.class);
//					if (bean != null && bean.getCode() == 0) {
//						mCurrentPage++;
//						if (position == 1) {//代表第一页
//							mData.clear();
//						}
//						if (bean.getData() != null && bean.getData().size() > 0) {
//							//添加集合 刷新适配器，通知加载框停止转动，并消失
//							mData.addAll(bean.getData());
//							Log.e("userid", mData.toString());
//							mAdapter.notifyDataSetChanged();
//						}
//						if (mData != null && mData.size() > 0) {
//							mTextViewEmply.setVisibility(View.GONE);
//							mRefreshLayout.setVisibility(View.VISIBLE);
//						} else {
//							mTextViewEmply.setVisibility(View.VISIBLE);
//							mRefreshLayout.setVisibility(View.GONE);
//						}
//						//通知加载框停止转动，并消失
//						mRefreshLayout.setRefreshing(false);
//					} else if (!TextUtils.isEmpty(bean.getError())) {
//						Toast.makeText(MySellActivity.this, bean.getError(), Toast.LENGTH_SHORT).show();
//					}
//				} else {
//					ToastUtils.showShort(MySellActivity.this, "Error:" + JsonUtils.getErrorMessage(response));
//				}
//			}
//		});
//	}

//	@Override
//	public void onClick(View v) {
//		if (v != null) {
//			Integer position = (Integer) v.getTag();
//			switch (v.getId()) {
//				case R.id.ll_my_sell_item://item
//					if (position != null) {
//						//单击一个新闻条目进行跳转
//						Intent intent = new Intent(MySellActivity.this, MySellDetailActivity.class);
//						intent.putExtra("goodid", mData.get(position).getId());
//						intent.putExtra("goodname", mData.get(position).getTitle());
//						startActivity(intent);
//					}
//					break;
//				case R.id.tv_my_sell_delete://已售卖
//					if (position != null) {
//						popWindow(position);
//					}
//					break;
//			}
//		}
//	}

//	private AlertDialog dialog;

//	private void popWindow(final Integer position) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		View view = getLayoutInflater().inflate(R.layout.dialog_reward, null);
//		TextView name = (TextView) view.findViewById(R.id.tv_name);
//		Button yes = (Button) view.findViewById(R.id.bt_dialog_yes);
//		Button no = (Button) view.findViewById(R.id.bt_dialog_no);
//
//		name.setText("交易成功?");
//		builder.setView(view).create();
//		dialog = builder.show();
//		yes.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				deleteData(position);
//			}
//		});
//		no.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//	}

	/**
	 * 将该商品设置成已售卖
	 *
	 * @param position
	 */
//	private void deleteData(final Integer position) {
//		String path = String.format(Api.BBS.BBS_MY_SELL_DELETE, mData.get(position).getId());
//		HttpUtils.post(this, path, null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				ToastUtils.showShort(MySellActivity.this, "网络请求错误:" + e.getMessage());
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				LogUtils.e(response);
//				if (JsonUtils.isSuccess(response)) {
//					dialog.dismiss();
//					try {
//						JSONObject object = new JSONObject(response);
//						NearbyNumberBean nldv = new NearbyNumberBean();
//						nldv.setData(object.getInt("data"));
//						nldv.setCode(object.getInt("code"));
//						nldv.setError(object.getString("error"));
//						if (nldv != null && nldv.getCode() == 0) {
//							if (nldv.getData() == 1) {
//								ToastUtils.showShort(MySellActivity.this, "交易成功");
//								Log.e("delete", position + "");
//								mData.get(position).setState(1);//已交易
//								mAdapter.delete(position);//刷新当前的item
//								mAdapter.notifyItemRangeChanged(position, mData.size() - (position));
//								if (mData != null && mData.size() > 0) {
//									mTextViewEmply.setVisibility(View.GONE);
//									mRefreshLayout.setVisibility(View.VISIBLE);
//								} else {
//									mTextViewEmply.setVisibility(View.VISIBLE);
//									mRefreshLayout.setVisibility(View.GONE);
//								}
//								dialog.dismiss();
//							} else {
//								ToastUtils.showShort(MySellActivity.this, "删除失败");
//							}
//						} else if (!TextUtils.isEmpty(nldv.getError())) {
//							ToastUtils.showShort(MySellActivity.this, nldv.getError());
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				} else {
//					ToastUtils.showShort(MySellActivity.this, "Error:" + JsonUtils.getErrorMessage(response));
//				}
//			}
//		});
//	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (requestCode == GET_CODE && resultCode == RESULT_OK) {
//			// 获取返回的数据
//			String type = data.getStringExtra("type");
//			if ("relesed".equals(type)) {
//				mTextViewEmply.setVisibility(View.GONE);
//				mCurrentPage = 1;
//				getNewsListData(1);
//				mRecyclerVertical.scrollToPosition(0);
//			}
//		}
//	}
}}
