package com.veeriyaperumal.bikeshowroom.model;

public class UserOption {
	private int level;
	private String option, bikeName;

	public UserOption(int level, String option) {
		this.level = level;
		this.option = option;
	}

	public UserOption(int level, String option, String bikeName) {
		this.level = level;
		this.option = option;
		this.bikeName = bikeName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}
}
