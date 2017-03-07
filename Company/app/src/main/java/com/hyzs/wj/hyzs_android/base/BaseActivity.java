package com.hyzs.wj.hyzs_android.base;


import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.hyzs.wj.hyzs_android.util.StatusBarUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		SDKInitializer.initialize(getApplicationContext());
		setContentView(getLayoutId());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ButterKnife.bind(this);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			StatusBarUtil.statusBarLightMode(this);
			StatusBarUtil.setStatusBarColor(this, android.R.color.white);
		}
		getSupportActionBar().hide();
		initData();
		initListener();
	}

	protected void initListener() {
	}
	protected abstract int getLayoutId();

	protected abstract void initData();
}
