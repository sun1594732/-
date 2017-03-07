package com.hyzs.wj.hyzs_android;

import android.app.Application;
import android.content.Context;

import com.hyzs.wj.hyzs_android.http.HttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者：zzw on 2016-10-13 13:33
 * QQ：1436942789
 * 邮箱：developer_zzw@163.com
 * 作用：${INPUT}
 */

public class MyApplication extends Application {
	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		//初始化OkHttp

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new LoggerInterceptor("HttpUtils"))
				.connectTimeout(10000L, TimeUnit.MILLISECONDS)
				.readTimeout(10000L, TimeUnit.MILLISECONDS)
				.build();
		HttpUtils.initClient(okHttpClient);

		mContext = getApplicationContext();
		//初始化ShareSDK
//		ShareSDK.initSDK(this, "17e55cc2368ec");
//		//初始化百度地图
//		SDKInitializer.initialize(getApplicationContext());
//		//初始化极光推送
//		JPushInterface.setDebugMode(true);
//		JPushInterface.init(this);
//		//注册蒲公英
//		PgyCrashManager.register(this);
//		//Leaks
//		LeakCanary.install(this);
	}

	public static Context getContext() {
		return mContext;
	}
}
