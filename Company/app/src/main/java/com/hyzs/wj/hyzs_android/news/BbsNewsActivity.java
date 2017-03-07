package com.hyzs.wj.hyzs_android.news;

import android.app.Dialog;
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


import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.hyzs.wj.hyzs_android.http.response.JsonResponseHandler;
import com.hyzs.wj.hyzs_android.util.DensityUtils;
import com.hyzs.wj.hyzs_android.widget.ProgressDialog;
import com.hyzs.wj.hyzs_android.widget.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/10.
 */

public class BbsNewsActivity extends BaseActivity implements View.OnClickListener {
	private boolean isadmin = false;//true : 管理员  false: 社区成员
	@BindView(R.id.tl_news_detail)
	TitleBar mTitleBar;
	@BindView(R.id.sl_bbs_news_content)
	SwipeRefreshLayout mRefreshLayout;
	@BindView(R.id.rv_bbs_news_content)
	RecyclerView mRecyclerVertical;
	@BindView(R.id.tv_bbs_news_emply)
	TextView mTextViewEmply;
    Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mProgressLoading.dismiss();
		}
	};
	private TextView released;
//	private BbsNewsAdapter mAdapter;
	private LinearLayoutManager mLayoutManager;
//	private List<BbsNewsBean.DataEntry> mData;
	private String bbsid;
	private int mCurrentPage = 0;
	private String mBbsName;
	private Dialog mProgressLoading;
	static final private int GET_CODE = 0;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_bbs_news;
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		mBbsName = intent.getStringExtra("bbsname");
		bbsid = intent.getStringExtra("bbsid");
		isadmin = intent.getBooleanExtra("isadmin",false);
		if(isadmin){
			released = new TextView(this);
			released.setText("发布");
			released.setTextColor(Color.parseColor("#1ccd9b"));
			released.setTextSize(14);
			released.setPadding(DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14), DensityUtils.dp2px(this, 14));
			mTitleBar.setRightView(released);
		}
		mTitleBar.setTitleText("社区新闻");
		mProgressLoading = ProgressDialog.createProgressLoading(this);
		mProgressLoading.show();
		handler.sendEmptyMessageDelayed(1,5000);
//		setData();
	}

	@Override
	protected void initListener() {
		super.initListener();
		//发布新闻
		if(isadmin){
			released.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(BbsNewsActivity.this,ReleasedNewsActivity.class);
					intent.putExtra("type","released");
					intent.putExtra("bbsname",mBbsName);
					startActivityForResult(intent,GET_CODE);
				}
			});
		}

//		mAdapter.setOnItemClickListener(this);

		//上拉加载
		mRecyclerVertical.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);

				//判断是否是停止滑动并且是滑到了最后一个item
				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
					// 判断最后一个条目是否出现了
					//mLayoutManager：LinearLayoutManager 布局管理器的对象
					int last = mLayoutManager.findLastVisibleItemPosition();
//					if (last == mData.size() - 1) {
//						//如果出现了 加载下一页数据
//						mRefreshLayout.setRefreshing(true);
//						getNewsListData(mCurrentPage);
//					}
				}
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
			}
		});
		//下拉刷新
		// 设置SwipeRefreshLayout 的监听和加载框的颜色
		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {

				mCurrentPage = 0;
//				getNewsListData(0);
			}
		});
		// 设置SwipeRefreshLayout 加载框的颜色
		mRefreshLayout.setColorSchemeColors
				(Color.parseColor("#1CCD9B"), Color.parseColor("#1CCD9B"), Color.parseColor("#1CCD9B"), Color.parseColor("#1CCD9B"));
	}


//	private void setData() {
//		mData = new ArrayList<>();
//		//请求数据
//		getNewsListData(0);
//		mAdapter = new BbsNewsAdapter(BbsNewsActivity.this, mData,isadmin);
//		mLayoutManager = new LinearLayoutManager(BbsNewsActivity.this, LinearLayoutManager.VERTICAL, false);
//		mRecyclerVertical.setLayoutManager(mLayoutManager);
//		mRecyclerVertical.setAdapter(mAdapter);
//	}

//	private void getNewsListData(final int position) {
//		String path = String.format(Api.BBS.BBS_NEWS_PATH, bbsid, position);
//		LogUtils.e("新闻"+path);
//		HttpUtils.get(this, path, null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				if(mProgressLoading != null){
//					mProgressLoading.dismiss();
//				}
//				ToastUtils.showShort(BbsNewsActivity.this, "网络请求错误:" + e.getMessage());
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				if(mProgressLoading != null){
//					mProgressLoading.dismiss();
//				}
//				if (JsonUtils.isSuccess(response)) {
//					BbsNewsBean bean = new Gson().fromJson(response, BbsNewsBean.class);
//					if (bean != null && bean.getCode() == 0) {
//						mCurrentPage++;
//						if (position == 0) {//代表第一页
//							mData.clear();
//						}
//						if (bean.getData() != null && bean.getData().size() > 0) {
//							//添加集合 刷新适配器，通知加载框停止转动，并消失
//							mData.addAll(bean.getData());
//							mAdapter.notifyDataSetChanged();
//						}
//						if(mData != null && mData.size() > 0){
//							mTextViewEmply.setVisibility(View.GONE);
//							mRefreshLayout.setVisibility(View.VISIBLE);
//						}else {
//							mTextViewEmply.setVisibility(View.VISIBLE);
//							mRefreshLayout.setVisibility(View.GONE);
//						}
//						//通知加载框停止转动，并消失
//						mRefreshLayout.setRefreshing(false);
//					} else if (!TextUtils.isEmpty(bean.getError())) {
//						Toast.makeText(BbsNewsActivity.this, bean.getError(), Toast.LENGTH_SHORT).show();
//					}
//				} else {
//					ToastUtils.showShort(BbsNewsActivity.this, "Error:" + JsonUtils.getErrorMessage(response));
//				}
//			}
//		});
//	}

	@Override
	public void onClick(View v) {
		if(v != null){
			Integer position = (Integer) v.getTag();
			switch (v.getId()) {
//			    case  R.id.ll_bbs_news:
//				    if (position!=null){
//					    //单击一个新闻条目进行跳转
//					    Intent intent = new Intent(BbsNewsActivity.this,NewsDetailWebActivity.class);
//					    intent.putExtra("id", mData.get(position).getId());
//					    intent.putExtra("type", 2);
//					    intent.putExtra("title",mData.get(position).getTitle());
//					    String img = mData.get(position).getImg();
//					    String[] imgs = img.split(",");
//					    if (imgs.length >= 1) {
//						    intent.putExtra("img",imgs[0]);
//					    }
//					    startActivity(intent);
//				    }
//			        break;
//			    case R.id.tv_delete://删除
//				    if (position!=null){
//					    popWindow(position);
//				    }
//			        break;
//			    case R.id.tv_alter://修改
//				    if (position!=null){
//					    Intent intent = new Intent(BbsNewsActivity.this,ReleasedNewsActivity.class);
//					    intent.putExtra("type","alter");
//					    intent.putExtra("id",mData.get(position).getId());//新闻详细页
//					    intent.putExtra("bbsname",mBbsName);
//					    startActivity(intent);
//				    }
//			        break;
			}
		}


	}

	private AlertDialog dialog;
//	private void popWindow(final Integer position) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		View view = getLayoutInflater().inflate(R.layout.dialog_reward, null);
//		TextView name = (TextView) view.findViewById(R.id.tv_name);
//		Button yes = (Button) view.findViewById(R.id.bt_dialog_yes);
//		Button no = (Button) view.findViewById(R.id.bt_dialog_no);
//
//		name.setText("是否删除该新闻");
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

//	private void deleteData(final Integer position) {
//		String path = String.format(Api.BBS.DELETE_NEWS_PATH,mData.get(position).getId());
//		HttpUtils.post(this, path, null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				ToastUtils.showShort(BbsNewsActivity.this, "网络请求错误:" + e.getMessage());
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
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
//								ToastUtils.showShort(BbsNewsActivity.this,"删除成功");
//								Log.e("delete",position+"");
//								mAdapter.delete(position);
//								mAdapter.notifyItemRangeChanged(position,mData.size() - position);
//								if(mData != null && mData.size() > 0){
//									mTextViewEmply.setVisibility(View.GONE);
//									mRefreshLayout.setVisibility(View.VISIBLE);
//								}else {
//									mTextViewEmply.setVisibility(View.VISIBLE);
//									mRefreshLayout.setVisibility(View.GONE);
//								}
//								dialog.dismiss();
//							} else{
//								ToastUtils.showShort(BbsNewsActivity.this,"删除失败");
//							}
//						} else if (!TextUtils.isEmpty(nldv.getError())) {
//							ToastUtils.showShort(BbsNewsActivity.this,nldv.getError());
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				} else {
//					ToastUtils.showShort(BbsNewsActivity.this, "Error:" + JsonUtils.getErrorMessage(response));
//				}
//			}
//		});
//	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data){
//		if (requestCode == GET_CODE && resultCode == RESULT_OK){
//			// 获取返回的数据
//			String type = data.getStringExtra("type");
//			if("relesed".equals(type)){
//				mTextViewEmply.setVisibility(View.GONE);
//				mCurrentPage = 0;
//				getNewsListData(0);
//				mRecyclerVertical.scrollToPosition(0);
//			}
//		}
//	}
}
