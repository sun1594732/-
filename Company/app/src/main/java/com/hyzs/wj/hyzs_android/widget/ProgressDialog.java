package com.hyzs.wj.hyzs_android.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hyzs.wj.hyzs_android.R;


/**
 * Created by Administrator on 2016/10/31.
 */

public class ProgressDialog {
	//帧动画
	public static Dialog createProgressLoading(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_progress, null);// 得到加载view
		//		RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.rl_dialog);// 加载布局
		//xml中的ImageView
		ImageView image = (ImageView) view.findViewById(R.id.iv_dialog_anim);
		image.setBackgroundResource(R.drawable.anim_dialog);
		// 加载动画
		// 通过ImageView对象拿到背景显示的AnimationDrawable
		final AnimationDrawable mAnimation = (AnimationDrawable) image.getBackground();
		// 为了防止在onCreate方法中只显示第一帧的解决方案之一
		image.post(new Runnable() {
			@Override
			public void run() {
				mAnimation.start();
			}
		});

		Dialog loadingDialog = new Dialog(context, R.style.MyDialog);// 创建自定义样式dialog
		loadingDialog.setCanceledOnTouchOutside(false);
		loadingDialog.setCancelable(false);// 可以用“返回键”取消
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		loadingDialog.setContentView(view, layoutParams);// 设置布局
		return loadingDialog;
	}
}
