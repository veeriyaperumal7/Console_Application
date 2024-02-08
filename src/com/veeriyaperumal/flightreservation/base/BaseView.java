package com.veeriyaperumal.flightreservation.base;

import java.util.Scanner;

public abstract class BaseView {
	private Scanner read = new Scanner(System.in);

	protected Scanner getScanner() {
		return read;
	}

	protected void printSeperatorLine() {
		System.out.println("-----------------------------------------------------------------");
	}

	protected void print(String content) {
		System.out.print(content);
	}

	protected void println(String content) {
		System.out.println(content);
	}

}
