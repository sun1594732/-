package com.hyzs.wj.hyzs_android.news;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.hyzs.wj.hyzs_android.http.response.JsonResponseHandler;
import com.hyzs.wj.hyzs_android.widget.ProgressDialog;
import com.hyzs.wj.hyzs_android.widget.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ReleasedNewsActivity extends BaseActivity {
	//0:title 1:单位 2:内容
	@BindViews({R.id.et_relesed_news_title, R.id.et_relesed_news_site, R.id.et_relesed_news_content})
	List<EditText> mEditTexts;
	@BindViews({R.id.iv_relesed_news_add01, R.id.iv_relesed_news_add02, R.id.iv_relesed_news_add03})
	List<ImageView> mImageViews;
	@BindView(R.id.tv_relesed_news_contentnum)
	TextView mTextViewContentNum;
	@BindView(R.id.tl_news_detail)
	TitleBar mTitleBar;

	private String type;
	private int tag;
	private Bitmap mBitmap01 = null, mBitmap02 = null, mBitmap03 = null;
	public final int REQUEST_CODE = 1;
	private String bbsid;// 社区id
	private Dialog mProgressLoading;
	private int nid;//新闻id
//	private BbsNewsItemDetailBean.DataEntry mItemData;
	private String mBbsName;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_released_news;
	}

	@Override
	protected void initData() {
//		bbsid = SPUtils.get(MyApplication.getContext(), "bbsid", "") + "";
		Intent intent = getIntent();
		type = intent.getStringExtra("type");
		mBbsName = intent.getStringExtra("bbsname");
		mEditTexts.get(1).setText(mBbsName);

		if ("alter".equals(type)) {//如果是修改
			nid = intent.getIntExtra("id", 0);
			mTitleBar.setTitleText("修改新闻");
//			getBbsNewsDetail();
		} else if ("released".equals(type)) {
			mTitleBar.setTitleText("发布新闻");
		}
	}

	@Override
	protected void initListener() {
		super.initListener();
		// 输入标题
		mEditTexts.get(0).addTextChangedListener(new TextWatcher() {
			int number = 0;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (before == 0) {
					number += count;
				} else {
					number -= before;
				}
				if (number > 20) {
//					ToastUtils.showShort(ReleasedNewsActivity.this, "不能超过20字");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		// 输入内容
		mEditTexts.get(2).addTextChangedListener(new TextWatcher() {
			int number = 0;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (before == 0) {
					number += count;
				} else {
					number -= before;
				}
				if (number <= 300) {
					mTextViewContentNum.setText(number + "");
				} else {
//					ToastUtils.showShort(ReleasedNewsActivity.this, "不能超过300字");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@OnClick({R.id.iv_relesed_news_add01, R.id.iv_relesed_news_add02, R.id.iv_relesed_news_add03, R.id.bt_relesed_news_released})
	public void onClick(View view) {
		if (view != null) {
			switch (view.getId()) {
				case R.id.iv_relesed_news_add01:
					tag = 1;
//					ImgSelActivity.startActivity(this, config, REQUEST_CODE);// 跳转到图片选择器
					break;
				case R.id.iv_relesed_news_add02:
					tag = 2;
//					ImgSelActivity.startActivity(this, config, REQUEST_CODE);// 跳转到图片选择器
					break;
				case R.id.iv_relesed_news_add03:
					tag = 3;
//					ImgSelActivity.startActivity(this, config, REQUEST_CODE);// 跳转到图片选择器
					break;
				case R.id.bt_relesed_news_released://发布
					//0:title 1:单位 2:内容
					String title = mEditTexts.get(0).getText().toString().trim();
					String contents = mEditTexts.get(2).getText().toString().trim();
					if ("".equals(title)) {
						Toast.makeText(ReleasedNewsActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
					} else if ("".equals(contents)) {
						Toast.makeText(ReleasedNewsActivity.this, "新闻内容不能为空", Toast.LENGTH_SHORT).show();
					} else {
						mProgressLoading = ProgressDialog.createProgressLoading(this);
						mProgressLoading.show();
//						updateTextMsg(title, contents, mBitmap01, mBitmap02, mBitmap03);
						break;
					}
			}
		}
	}

	/**
	 * 提交社区新闻
	 *
//	 * @param title
//	 * @param contents
//	 * @param bitmap
	 */
//	private void updateTextMsg(final String title, final String contents, final Bitmap... bitmap) {
//		Map<String, String> map = new HashMap<>();
////		String userid = SPUtils.get(this, "userid", "").toString();
//		map.put("sid", bbsid);//sid：社区ID
////		map.put("uid", userid);//uid：当前用户　
//		map.put("title", title);//title：标题
//		map.put("contents", contents);//contents：内容
////		String image1 = InPutUtils.bitmaptoString(bitmap[0]);
////		String image2 = InPutUtils.bitmaptoString(bitmap[1]);
////		String image3 = InPutUtils.bitmaptoString(bitmap[2]);
//		if (!TextUtils.isEmpty(image1)) {
//			map.put("img1", image1);
//		} else {
//			map.put("img1", "");
//		}
//		if (!TextUtils.isEmpty(image2)) {
//			map.put("img2", image2);
//		} else {
//			map.put("img2", "");
//		}
//		if (!TextUtils.isEmpty(image3)) {
//			map.put("img3", image3);
//		}else {
//			map.put("img3", "");
//		}
//		Log.e("TAGS","sid = " + bbsid + " ; uid = " + userid + " ; title = " + title + " ; contents = " + contents + " ; img1 = " + image1 + " ; img2 = " + image2+ " ; img3 = " + image3);
//		String path = null;
//		if ("alter".equals(type)) {
//			map.put("nid", nid + "");//新闻id
//			Log.e("content", "sid == " + bbsid + " ; uid == " + userid + " ; title == " + title + " ; contents == " + contents + " ; nid == " + nid);
//			path = Api.BBS.BBS_ALTER_NEWS_PATH;
//		} else if ("released".equals(type)) {
//			path = Api.BBS.BBS_RELEASED_NEWS_PATH;
//		}
//
//		HttpUtils.post(this, path, map, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				if (mProgressLoading != null) {
//					mProgressLoading.dismiss();
//				}
//				ToastUtils.showShort(ReleasedNewsActivity.this, "网络请求错误:" + e.getMessage());
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				if (mProgressLoading != null) {
//					mProgressLoading.dismiss();
//				}
//				if (JsonUtils.isSuccess(response)) {
//					try {
//						JSONObject object = new JSONObject(response);
//						NearbyNumberBean nldv = new NearbyNumberBean();
//						nldv.setData(object.getInt("data"));
//						nldv.setCode(object.getInt("code"));
//						nldv.setError(object.getString("error"));
//						if (nldv != null && nldv.getCode() == 0) {
//							if ("alter".equals(type)) {
//								if (nldv.getData() == 1) {
//									ToastUtils.showShort(ReleasedNewsActivity.this, "修改成功");
//									intentActivity();
//								} else {
//									ToastUtils.showShort(ReleasedNewsActivity.this, "修改失败");
//								}
//							} else if ("released".equals(type)) {
//								if (nldv.getData() > 0) {
//									ToastUtils.showShort(ReleasedNewsActivity.this, "发布成功");
//									intentActivity();
//								} else {
//									ToastUtils.showShort(ReleasedNewsActivity.this, "发布失败");
//								}
//							}
//						} else if (!TextUtils.isEmpty(nldv.getError())) {
//							ToastUtils.showShort(ReleasedNewsActivity.this, nldv.getError());
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				} else {
//					ToastUtils.showShort(ReleasedNewsActivity.this, "Error:" + JsonUtils.getErrorMessage(response));
//				}
//			}
//		});
//	}

	public void intentActivity() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent();
				intent.putExtra("type", "relesed");
				setResult(RESULT_OK, intent);
				finish();
			}
		}, 100);
	}

	/**
	 * 修改社区新闻时显示的内容
	 */
//	private void getBbsNewsDetail() {
//		String path = String.format(Api.BBS.BBS_NEWS_DETAIL_ITEM, nid);
//		HttpUtils.get(this, path, null, new JsonResponseHandler() {
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				ToastUtils.showShort(ReleasedNewsActivity.this, "网络请求错误:" + e.getMessage());
//			}
//
//			@Override
//			public void onSuccess(String response, int id) {
//				if (JsonUtils.isSuccess(response)) {
//					BbsNewsItemDetailBean bean = new Gson().fromJson(response, BbsNewsItemDetailBean.class);
//					if (bean != null && bean.getCode() == 0) {
//						mItemData = bean.getData();
//						//0:title 1:单位 2:内容
//						mEditTexts.get(0).setText(mItemData.getTitle());
//						mEditTexts.get(2).setText(Html.fromHtml(mItemData.getContents()));
//						// 显示求助图片
//						String img = mItemData.getImg();
//						String[] imgs = img.split(",");
//						if (imgs.length == 1) {
//							Glide.with(ReleasedNewsActivity.this).load(img).placeholder(R.drawable.loading_top).error(R.drawable.loading_top).into(mImageViews.get(0));
//						} else if (imgs.length == 2) {// 有两张图片
//							Glide.with(ReleasedNewsActivity.this).load(imgs[0]).placeholder(R.drawable.loading_top).error(R.drawable.loading_top).into(mImageViews.get(0));
//							Glide.with(ReleasedNewsActivity.this).load(imgs[1]).placeholder(R.drawable.loading_top).error(R.drawable.loading_top).into(mImageViews.get(1));
//						} else if (imgs.length == 3) {// 有三张图片
//							Glide.with(ReleasedNewsActivity.this).load(imgs[0]).placeholder(R.drawable.loading_top).error(R.drawable.loading_top).into(mImageViews.get(0));
//							Glide.with(ReleasedNewsActivity.this).load(imgs[1]).placeholder(R.drawable.loading_top).error(R.drawable.loading_top).into(mImageViews.get(1));
//							Glide.with(ReleasedNewsActivity.this).load(imgs[2]).placeholder(R.drawable.loading_top).error(R.drawable.loading_top).into(mImageViews.get(2));
//						}
//					}
//				} else {
//					ToastUtils.showShort(ReleasedNewsActivity.this, "Error:" + JsonUtils.getErrorMessage(response));
//				}
//			}
//		});
//	}

	/**
	 * 图片选择器
	 *
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 图片选择结果回调
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//			List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
//			LogUtils.e("stringExtra==" + pathList.get(0));
//			Bitmap bitmap = BitmapUtils.compressImageFromFile(pathList.get(0));
//			updateUserImage(bitmap);
		}
	}

	/**
	 * 更新头像
	 */
	private void updateUserImage(final Bitmap imagePath) {
//		ToastUtils.showShort(ReleasedNewsActivity.this, "图片设置成功");
		if (tag == 1) {//左边图片
			mImageViews.get(0).setImageBitmap(imagePath);
			mBitmap01 = imagePath;
		} else if (tag == 2) { //中间的图片
			mImageViews.get(1).setImageBitmap(imagePath);
			mBitmap02 = imagePath;
		} else if (tag == 3) { //右边的图片
			mImageViews.get(2).setImageBitmap(imagePath);
			mBitmap03 = imagePath;
		}
	}

	// 自定义图片加载器
//	private ImageLoader loader = new ImageLoader() {
//		@Override
//		public void displayImage(Context context, String path, ImageView imageView) {
//			// TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
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
//			//			.backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
//			.backResId(R.drawable.ic_back)
//			// 标题
//			.title("图片")
//			// 标题文字颜色
//			.titleColor(Color.WHITE)
//			// TitleBar背景色
//			.titleBgColor(Color.parseColor("#3F51B5"))
//			// 裁剪大小。needCrop为true的时候配置
//			.cropSize(1, 1, 200, 200)
//			.needCrop(false)
//			// 第一个是否显示相机
//			.needCamera(true)
//			// 最大选择图片数量
//			.maxNum(1)
//			.build();

	@Override
	protected void onDestroy() {
		if (mBitmap01 != null) {
			mBitmap01.recycle();
		}
		if (mBitmap02 != null) {
			mBitmap02.recycle();
		}
		if (mBitmap03 != null) {
			mBitmap03.recycle();
		}
		super.onDestroy();
	}
}
