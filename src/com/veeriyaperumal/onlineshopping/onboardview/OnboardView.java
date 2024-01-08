package com.veeriyaperumal.onlineshopping.onboardview;

import com.veeriyaperumal.onlineshopping.base.BaseView;


public class OnboardView extends BaseView {

	private OnboardViewModel OnboardViewModel;
	
	public OnboardView() {
		this.OnboardViewModel = new OnboardViewModel(this);
	}
	
	 public void showOnBoardOptions() {
		 options.clear();
		 options.add("Sign in");
		 options.add("Sign up");
		printOptionsTable(options, "ONBOARD OPTIONS");
	}

}
