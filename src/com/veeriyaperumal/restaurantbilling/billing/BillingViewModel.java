package com.veeriyaperumal.restaurantbilling.billing;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.veeriyaperumal.restaurantbilling.model.Bill;
import com.veeriyaperumal.restaurantbilling.model.Dish;
import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class BillingViewModel {

	private BillingView billingView;
	private Bill currentBill = null;

	public Bill getCurrentBill() {
		return currentBill;
	}

	public BillingViewModel(BillingView billingView) {
		this.billingView = billingView;
	}

	public BillingView getBillingView() {
		return billingView;
	}

	public List<Dish> getDishList() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().getDishList();

	}

	public void intializeBill() throws ClassNotFoundException, SQLException {
		currentBill = new Bill();
		currentBill.setBillNo(Repository.getInstance().getNewBillNo());
		currentBill.setBillDate(LocalDate.now());
	}

	public boolean addDishToBill(int dishId, int quantity) throws ClassNotFoundException, SQLException {
		for (Dish dish : currentBill.getPurchasedDish()) {
			if (dishId == dish.getDishId()) {
				dish.setQuantity(dish.getQuantity() + quantity);
				currentBill.setBillPrice((dish.getPrice() * quantity) + currentBill.getBillPrice());
				return true;
			}
		}

		for (Dish dish : Repository.getInstance().getDishList()) {
			if (dishId == dish.getDishId()) {
				Dish newDish = new Dish(dish);
				newDish.setQuantity(quantity);
				currentBill.getPurchasedDish().add(newDish);
				currentBill.setBillPrice((newDish.getPrice() * quantity) + currentBill.getBillPrice());
				return true;
			}
		}
		return false;
	}

	public boolean removeDish(int serialNo) {
		try {
			Float priceDeductAmount = currentBill.getPurchasedDish().get(serialNo - 1).getPrice()
					* currentBill.getPurchasedDish().get(serialNo - 1).getQuantity();
			currentBill.setBillPrice(currentBill.getBillPrice() - priceDeductAmount);
			currentBill.getPurchasedDish().remove(serialNo - 1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean finsihBill() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().finishBill(currentBill);
	}

	public void cancelCurrentBill() {
		currentBill = null;
	}

}
