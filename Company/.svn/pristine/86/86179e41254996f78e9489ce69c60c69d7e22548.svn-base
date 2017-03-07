package com.hyzs.wj.hyzs_android.trade;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.hyzs.wj.hyzs_android.MyApplication;
import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.hyzs.wj.hyzs_android.http.response.JsonResponseHandler;
import com.hyzs.wj.hyzs_android.widget.ProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 发布我的售卖
 */
public class TradeMyActivity extends BaseActivity {
	@BindView(R.id.back)
	ImageView mBack;
	@BindView(R.id.cancle)
	TextView mCancle;
	@BindView(R.id.edtv_title)
	EditText mEdtvName;
	@BindView(R.id.edtv_price)
	EditText mEdtvPrice;
	@BindView(R.id.edtv_phone)
	EditText mEdtvPhone;
	@BindView(R.id.iv_text_add01)
	ImageView mIvTextAdd01;
	@BindView(R.id.iv_text_add02)
	ImageView mIvTextAdd02;
	@BindView(R.id.iv_text_add03)
	ImageView mIvTextAdd03;
	@BindView(R.id.et_push_content)
	EditText mEtPushContent;
	@BindView(R.id.xiaoxi_but)
	ToggleButton mXiaoxiBut;
	@BindView(R.id.btn_commit)
	Button mBtnCommit;
//	@BindView(R.id.id_flowlayout)
//	TagFlowLayout mFlowLayout;


//	private Bitmap bitmap1, bitmap2, bitmap3;
//	private int isCanChange = 0; //1 支持 0不支持
	/**
	 * 商品类型 1生活 2家电 3数码 4 衣物 5其他
	 */
//	private List<Integer> type;
//	private Dialog mLoadingPage;
//	private int index;
//	private boolean flage = true;
//	private List<NewsTypeBean.DataBean> mTypeBean;
//	private List<String> titles;
//	private int typeIndex = 0;
//	private String typex;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_trade_my;
	}

	@Override
	protected void initData() {
//		getTitleData();
//		InPutUtils.setPricePoint(mEdtvPrice);
		mXiaoxiBut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				if (isChecked) {
//					isCanChange = 1;
//				} else {
//					isCanChange = 0;
//				}
			}
		});
	}

//	private void getTitleData() {
//		HttpUtils.get(MyApplication.getContext(), Api.BBS.GET_TADE_TYPE_ALL+"?stype=0", null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//
//			}
//
//			@Override
//			public void onSuccess(String content, int id) {
//				if (JsonUtils.isSuccess(content)) {
//					NewsTypeBean typeBean = new Gson().fromJson(content, NewsTypeBean.class);
//					mTypeBean = typeBean.getData();
//
//					titles = new ArrayList<>();
//					type = new ArrayList<>();
//					for (int i = 0; i < mTypeBean.size(); i++) {
//						titles.add(mTypeBean.get(i).getName());//类型
//						type.add(mTypeBean.get(i).getId());//id
//					}
//
//					mFlowLayout.setAdapter(new TagAdapter<String>(titles) {
//						@Override
//						public View getView(FlowLayout parent, int position, String s) {
//							final LayoutInflater mInflater = LayoutInflater.from(TradeMyActivity.this);
//							TextView tv = (TextView) mInflater.inflate(R.layout.tv,
//									mFlowLayout, false);
//							tv.setText(s);
//							return tv;
//						}
//					});
//					mFlowLayout.setMaxSelectCount(1);
//					mFlowLayout.getId();
//					mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//						@Override
//						public boolean onTagClick(View view, int position, FlowLayout parent) {
//							typeIndex = type.get(position);
//							return false;
//						}
//					});
//
//				} else {
//					ToastUtils.showShort(TradeMyActivity.this, "Error:" + JsonUtils.getErrorMessage(content));
//				}
//			}
//		});
//
//	}

	@OnClick({R.id.back, R.id.cancle, R.id.iv_text_add01, R.id.iv_text_add02, R.id.iv_text_add03, R.id.xiaoxi_but, R.id.btn_commit})
//	public void onClick(View view) {
//		switch (view.getId()) {
//			case R.id.back:
//			case R.id.cancle:
//				finish();
//				break;
//			case R.id.iv_text_add01:
//				ImgSelActivity.startActivity(this, config, REQUEST_CODE);
//				index = 1;
//				break;
//			case R.id.iv_text_add02:
//				ImgSelActivity.startActivity(this, config, REQUEST_CODE);
//				index = 2;
//				break;
//			case R.id.iv_text_add03:
//				ImgSelActivity.startActivity(this, config, REQUEST_CODE);
//				index = 3;
//				break;
//			case R.id.xiaoxi_but://是否支持交换物件选择
//				break;
//			case R.id.btn_commit:
//				getInputMessage();
//				break;
//		}
//	}

	/**
	 * 发布置换物品
	 * title：物品名称 price：物品价格 contents：
	 * 简介 phone：联系方式 type：物品类型 isexchange：是否支持交换（1支持，0不支持）
	 * sqid：发布人所属小区 uid：发布人id img1,img2……：上传图片
	 */
//	private void getInputMessage() {
//		String name = mEdtvName.getText().toString();
//		String phone = mEdtvPhone.getText().toString();
//		String info = mEtPushContent.getText().toString();
//		// 通过正则表达式获得赏金
//		String price = mEdtvPrice.getText().toString();
//
//		if ("".equals(name)) {
//			Toast.makeText(TradeMyActivity.this, "物品名称不能为空", Toast.LENGTH_SHORT).show();
//			return;
//		} else if ("".equals(price)) {
//			Toast.makeText(TradeMyActivity.this, "价格不能为空", Toast.LENGTH_SHORT).show();
//			return;
//		} else if (phone.length() != 11 && !InPutUtils.isMobilePhone(phone)) {
//			Toast.makeText(TradeMyActivity.this, "请再次检查手机号码", Toast.LENGTH_SHORT).show();
//			return;
//		} else if ("".equals(info)) {
//			Toast.makeText(TradeMyActivity.this, "物品简介不能为空", Toast.LENGTH_SHORT).show();
//			return;
//		} else if (flage) {
//			Toast.makeText(TradeMyActivity.this, "您至少需要上传一张图片", Toast.LENGTH_SHORT).show();
//		} else {
//			mLoadingPage = ProgressDialog.createProgressLoading(this);
//			mLoadingPage.show();
//			Map requestParams = new HashMap();
//			requestParams.put("title", name);
//			requestParams.put("price", price);
//			requestParams.put("contents", info);
//			requestParams.put("phone", phone);
//			requestParams.put("type", typeIndex + "");
//			requestParams.put("isexchange", isCanChange + "");
//			requestParams.put("sqid", SPUtils.get(MyApplication.getContext(), "bbsid", ""));
//			requestParams.put("uid", SPUtils.get(MyApplication.getContext(), "userid", ""));
//
//			String image1 = InPutUtils.bitmaptoString(bitmap1);
//			String image2 = InPutUtils.bitmaptoString(bitmap2);
//			String image3 = InPutUtils.bitmaptoString(bitmap3);
//			if (!TextUtils.isEmpty(image1)) {
//				requestParams.put("img1", image1);
//			}
//			if (!TextUtils.isEmpty(image2)) {
//				if (TextUtils.isEmpty(image1)) {
//					image1 = image2;
//					requestParams.put("img1", image1);
//					image2 = "";
//				} else {
//					requestParams.put("img2", image2);
//				}
//			}
//			if (!TextUtils.isEmpty(image3)) {
//				if (TextUtils.isEmpty(image1)) {
//					requestParams.put("img1", image3);
//				} else if (TextUtils.isEmpty(image2)) {
//					requestParams.put("img2", image3);
//				} else {
//					requestParams.put("img3", image3);
//				}
//			}
//			HttpUtils.post(MyApplication.getContext(), Api.BBS.CREATE_TRADE+"stype="+0, requestParams, new JsonResponseHandler() {
//				@Override
//				public void onError(Call call, Exception e, int id) {
//					Toast.makeText(TradeMyActivity.this, "网络访问错误", Toast.LENGTH_SHORT).show();
//				}
//
//				@Override
//				public void onSuccess(String response, int id) {
//					Toast.makeText(TradeMyActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
//					Timer timer = new Timer();
//					timer.schedule(new TimerTask() {
//						@Override
//						public void run() {
//							Intent intent = new Intent();
//							intent.putExtra("type", "relesed");
//							setResult(RESULT_OK, intent);
//							finish();
//						}
//					}, 100);
//					mLoadingPage.dismiss();
//				}
//			});
//		}
//	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		// 图片选择结果回调
//		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//			List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
//			LogUtils.e("stringExtra==" + pathList.get(0));
//			flage = false;
//			if (index == 1) {
//				bitmap1 = BitmapUtils.compressImageFromFile(pathList.get(0));
//				mIvTextAdd01.setImageBitmap(bitmap1);
//			}
//			if (index == 2) {
//				bitmap2 = BitmapUtils.compressImageFromFile(pathList.get(0));
//				mIvTextAdd02.setImageBitmap(bitmap2);
//			}
//			if (index == 3) {
//				bitmap3 = BitmapUtils.compressImageFromFile(pathList.get(0));
//				mIvTextAdd03.setImageBitmap(bitmap3);
//			}
//		}
//	}
//
//	// 自定义图片加载器
//	private ImageLoader loader = new ImageLoader() {
//		@Override
//		public void displayImage(Context context, String path, ImageView imageView) {
//			Glide.with(context).load(path).into(imageView);
//		}
//	};
//	// 自由配置选项
//	ImgSelConfig config = new ImgSelConfig.Builder(loader)
//			// 是否多选
//			.multiSelect(false)
//			// “确定”按钮背景色
//			.btnBgColor(Color.GRAY)
//			// “确定”按钮文字颜色
//			.btnTextColor(Color.WHITE)
//			// 使用沉浸式状态栏
//			.statusBarColor(Color.parseColor("#3F51B5"))
//			// 返回图标ResId
//			//						.backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
//			.backResId(R.drawable.ic_back)
//			// 标题
//			.title("图片")
//			// 标题文字颜色
//			.titleColor(Color.WHITE)
//			// TitleBar背景色
//			.titleBgColor(Color.parseColor("#3F51B5"))
//			// 裁剪大小。needCrop为true的时候配置
//			.cropSize(0, 0, 0, 0)
//			.needCrop(false)
//			// 第一个是否显示相机
//			.needCamera(true)
//			// 最大选择图片数量
//			.maxNum(1)
//			.build();

	@Override
	protected void onDestroy() {
//		if (bitmap1 != null) {
//			bitmap1.recycle();
//		}
//		if (bitmap2 != null) {
//			bitmap2.recycle();
//		}
//		if (bitmap3 != null) {
//			bitmap3.recycle();
//		}
		super.onDestroy();
	}
}
