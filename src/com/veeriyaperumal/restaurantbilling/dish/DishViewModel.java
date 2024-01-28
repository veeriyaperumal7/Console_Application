package com.veeriyaperumal.restaurantbilling.dish;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.veeriyaperumal.restaurantbilling.model.Dish;
import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class DishViewModel {

	private DishView dishView;
	private ArrayList<Dish> dishList;
	private Dish userSelectedDish;

	public DishViewModel(DishView dishView) {
		this.dishView = dishView;
	}

	public boolean addDish(Dish newDish) throws ClassNotFoundException, SQLException {
		return Repository.getInstance().addDish(newDish);
	}

	public List<Dish> getDishList() throws ClassNotFoundException, SQLException {
		dishList = Repository.getInstance().getDishList();
		return dishList;

	}

	public boolean isValidDishId(int selectedDishId) {
		for (Dish dish : dishList) {
			if (dish.getDishId() == selectedDishId) {
				userSelectedDish = dish;
				return true;
			}
		}
		return false;
	}

	public Dish getUserSelectedDish() {
		return userSelectedDish;
	}

	public boolean updateDishData() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().updateDishData(userSelectedDish);
	}

	public boolean removeDish() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().removeDish(userSelectedDish);
	}

}
