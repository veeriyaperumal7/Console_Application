package com.veeriyaperumal.hotelbilling.dish;

import java.sql.SQLException;

import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.model.Dish;

public class DishView extends BaseView {

	private DishViewModel dishViewModel;

	public DishView() {
		this.dishViewModel = new DishViewModel(this);
	}

	public void showDishOptions() {
		menuOptions.clear();
		menuOptions.add("Add Dish");
		menuOptions.add("Update Dish Details");
		menuOptions.add("Remove Dish");
		printOptionsTable(menuOptions, "Dish Options");
		print("Enter your option : ");
		chooseFeature(menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1));
	}

	private void chooseFeature(String option) {
		switch (option) {
		case "Add Dish":
			addDish();
			break;
		case "Update Dish Details":
			updateDishDetails();
			break;
		case "Remove Dish":
			removeDish();
			break;
		}

	}

	private void removeDish() {
		try {
			printDishTable(dishViewModel.getDishList(), "Dishes");
			if (dishViewModel.getDishList() == null || dishViewModel.getDishList().size() < 1) {
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			printErrorMessage("Error while fetching dish data...");
			e.printStackTrace();
		}

		print("Enter dish id : ");
		int selectedDishId;
		do {
			selectedDishId = getIntegerInput("Choose valid dish id : ");
			if (dishViewModel.isValidDishId(selectedDishId)) {
				break;
			} else {
				showWrongSelectionMessage("Choose valid dish id : ");
			}
		} while (true);
		try {
			if (dishViewModel.removeDish()) {
				printSuccesMessage("Dish removed successfully...");
			} else {
				printUserWarningMessage("Dish not removed...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while remove dish data...");
		}
	}

	private void updateDishDetails() {
		try {
			printDishTable(dishViewModel.getDishList(), "Dishes");
			if (dishViewModel.getDishList() == null || dishViewModel.getDishList().size() < 1) {
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			printErrorMessage("Error while fetching dish data...");
			e.printStackTrace();
		}
		print("Enter dish id : ");
		int selectedDishId;
		do {
			selectedDishId = getIntegerInput("Choose valid dish id : ");
			if (dishViewModel.isValidDishId(selectedDishId)) {
				break;
			} else {
				showWrongSelectionMessage("Choose valid dish id : ");
			}
		} while (true);

		menuOptions.clear();
		menuOptions.add("Update Dish Name");
		menuOptions.add("Update Dish Price");
		printOptionsTable(menuOptions, "Update Options");
		switch (menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1)) {
		case "Update Dish Name":
			dishViewModel.getUserSelectedDish().setDishName(getDishName());
			break;
		case "Update Dish Price":
			dishViewModel.getUserSelectedDish().setPrice(getDishPrice());
			break;
		}
		try {
			if (dishViewModel.updateDishData()) {
				printSuccesMessage("Dish data updated successfully...");
			} else {
				printUserWarningMessage("Dish data not updated...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while updating dish data...");
		}
	}

	private void addDish() {
		Dish newDish = new Dish();
		newDish.setDishName(getDishName());
		newDish.setPrice(getDishPrice());
		try {
			if (dishViewModel.addDish(newDish)) {
				printSuccesMessage("Dish successfully added...");
			} else {
				printUserWarningMessage("Dish not added...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while adding new dish...");
		}
	}

	private String getDishName() {
		print("Enter dish name : ");
		return getStringInput("Enter valid dish name : ");
	}

	private float getDishPrice() {
		print("Enter dish price : ");
		return getFloatInput("Enter valid price : ", 1, Float.MAX_VALUE);
	}

}
