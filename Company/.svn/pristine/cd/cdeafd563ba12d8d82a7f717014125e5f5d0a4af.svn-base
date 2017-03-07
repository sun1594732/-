package com.hyzs.wj.hyzs_android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyzs.wj.hyzs_android.R;
import com.hyzs.wj.hyzs_android.base.BaseActivity;
import com.hyzs.wj.hyzs_android.util.DensityUtils;


/**
 * 作者：zzw on 2016-11-01 10:06
 * QQ：1436942789
 * 邮箱：developer_zzw@163.com
 * 作用：TitleBar标题栏
 */

public class TitleBar extends RelativeLayout implements View.OnClickListener {

	private RelativeLayout.LayoutParams mLeftViewParams;
	private RelativeLayout.LayoutParams mRightViewParams;
	private RelativeLayout.LayoutParams mCenterViewParams;

	private ImageView iv_title_back;
	private TextView tv_title_text;
	private View title_divider;
	private View mLeftView;
	private View mRightView;
	private View mCenterView;

	private Context mContext;

	public TitleBar(Context context) {
		this(context, null);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		inflate(mContext, R.layout.title_bar, this);
		iv_title_back = (ImageView) findViewById(R.id.iv_title_back);
		tv_title_text = (TextView) findViewById(R.id.tv_title_text);
		title_divider = findViewById(R.id.title_divider);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void init() {

		if (mContext instanceof BaseActivity) {
			iv_title_back.setOnClickListener(this);
		} else {
			this.removeView(iv_title_back);
		}
		mLeftViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		mLeftViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
		mLeftViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		mRightViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		mRightViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
		mRightViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		mCenterViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		mCenterViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
		mCenterViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
	}

	/**
	 * 隐藏分割线
	 */
	public void hideDivider() {
		if (title_divider != null) {
			title_divider.setVisibility(INVISIBLE);
		}
	}

	/**
	 * 设置中间的View
	 *
	 * @param centerView
	 */
	public void setCenterView(View centerView) {
		removeView(tv_title_text);
		centerView.setLayoutParams(mCenterViewParams);
		mCenterView = centerView;
		addView(mCenterView);
	}

	/**
	 * 设置左边的View
	 *
	 * @param leftView
	 */
	public void setLeftView(View leftView) {
		removeView(iv_title_back);
		leftView.setLayoutParams(mLeftViewParams);
		mLeftView = leftView;
		addView(leftView);
	}

	/**
	 * 设置右边的View
	 *
	 * @param rightView
	 */
	public void setRightView(View rightView) {
		rightView.setLayoutParams(mRightViewParams);
		mRightView = rightView;
		mRightView.setPadding(DensityUtils.dp2px(mContext, 15), DensityUtils.dp2px(mContext, 15), DensityUtils.dp2px(mContext, 15), DensityUtils.dp2px(mContext, 15));
		addView(mRightView);
	}

	/**
	 * 移除退出键
	 */
	public void removeBack() {
		if (iv_title_back != null) {
			iv_title_back.setVisibility(GONE);
			removeView(iv_title_back);
		}
	}

	/**
	 * 设置标题栏标题
	 *
	 * @param text
	 */
	public void setTitleText(CharSequence text) {
		tv_title_text.setText(text);
	}

	/**
	 * 结束当前Activity
	 *
	 * @param v
	 */
	@Override
	public void onClick(View v) {
	}
}
