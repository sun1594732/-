package com.hyzs.wj.hyzs_android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by 14369 on 2016-09-09.
 */

public abstract class BaseFragment extends Fragment {
	
	private LoadingPage mLoadingPage;
	public Activity mActivity;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if (bundle != null) {
			getArgs(bundle);
		}
	}

	/**
	 * 获取参数
	 *
	 * @param bundle
	 */
	public void getArgs(Bundle bundle) {

	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mLoadingPage = new LoadingPage(getActivity()) {
			@Override
			public int layoutId() {
				return getLayoutId();
			}
			
			@Override
			protected void onSuccess(ResultState resultState, View successView) {
				ButterKnife.bind(BaseFragment.this, successView);
				initView(successView);
				initData(resultState.getContent());
			}
			
			@Override
			protected Map<String, String> params() {
				return getParams();
			}
			
			@Override
			protected String url() {
				return getUrl();
			}
		};
		return mLoadingPage;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		showLoadingPage();
	}
	
	public void showLoadingPage() {
		mLoadingPage.show();
	}
	
	protected void initView(View contentView) {
	}
	
	protected abstract Map<String, String> getParams();
	
	protected abstract void initData(String content);
	
	protected abstract String getUrl();
	
	public abstract int getLayoutId();
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mActivity = (Activity) context;
	}
}
