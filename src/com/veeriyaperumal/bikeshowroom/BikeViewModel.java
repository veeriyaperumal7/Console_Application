package com.veeriyaperumal.bikeshowroom;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import com.veeriyaperumal.bikeshowroom.model.Bike;
import com.veeriyaperumal.bikeshowroom.model.UserOption;
import com.veeriyaperumal.bikeshowroom.repository.Repository;

public class BikeViewModel {
	private Bike bike;
	private BikeShowroomView showRoomView;
	private Stack<UserOption> chatHistory;
	private List<String> menuList;

	public BikeViewModel(BikeShowroomView bikeShowroomView) {
		this.showRoomView = bikeShowroomView;
		chatHistory = new Stack<>();
		menuList = new ArrayList<>();
		promoteLevel(0);
	}

	private void dePromoteLevel() {
		if (!chatHistory.isEmpty()) {
			chatHistory.pop();
		}
	}

	private void promoteLevel(int choice) {
		if (chatHistory.isEmpty()) {
			chatHistory.push(new UserOption(0, ""));
		} else {
			UserOption temp = chatHistory.peek();
			if (temp.getLevel() + 1 == 3) { // For store selected bike name in a array
				chatHistory.push(new UserOption(temp.getLevel() + 1, temp.getOption() + Integer.toString(choice),
						menuList.get(choice - 1)));
			} else {
				chatHistory.push(new UserOption(temp.getLevel() + 1, temp.getOption() + Integer.toString(choice)));
			}
		}
	}

	private void clearChat() { // When user want to terminate the program.
		chatHistory.clear();
	}

	public UserOption getTopOfChatHistory() {
		if (chatHistory.isEmpty()) {
			return null;
		} else {
			return chatHistory.peek();
		}
	}

	public List<String> getChoices() {
		menuList = Repository.getInstance(this).getMenus();
		return menuList;
	}

	public void showBikeData(String bikeData) {
		showRoomView.printBikeData(bikeData);
	}

	public boolean validateInput(int userEnteredChoice) {
		if (userEnteredChoice >= 1 && userEnteredChoice <= menuList.size()) {
			return true;
		} else {
			return false;
		}
	}

	public void chooseLevel(int userEnteredInput) {
		if (menuList.get(userEnteredInput - 1).equals("Exit")) {
			clearChat();
		} else if (menuList.get(userEnteredInput - 1).equals("Go back")) {
			dePromoteLevel();
		} else {
			promoteLevel(userEnteredInput);
		}
	}

	public void terminateProgramWithMessage(String errorMessage) {
		showRoomView.printErrorMessage(errorMessage);
		System.exit(0);
	}

}
