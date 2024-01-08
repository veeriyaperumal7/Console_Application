package com.veeriyaperumal.inventorymanagement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.veeriyaperumal.inventorymanagement.databaseconnetion.JdbcConnection;
import com.veeriyaperumal.inventorymanagement.model.Product;

public class Repository {
	private String query;
	private static Repository repository;
	private HashMap<Integer, Product> productList = new HashMap<>();
	private HashMap<Integer, Product> productStockList = new HashMap<>();

	private Repository() {
		productList = new HashMap<>();
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean addProduct(Product product) {
		int rowsAffected = 0;
		query = "insert into product_info (product_name, product_unit, product_price, product_status) values('"
				+ product.getProductName().replace("'", "''") + "','" + product.getUnit().replace("'", "''") + "','"
				+ product.getPrice() + "','ONLINE')";

		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
		}
		return (rowsAffected >= 1) ? true : false;
	}

	public HashMap<Integer, Product> getProductList() {
		productList.clear();
		query = "SELECT * FROM product_info WHERE  product_status='ONLINE' order by product_id";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			productList = null;
		} else {
			try {
				while (result.next()) {
					Product product = new Product();
					product.setId(result.getInt("product_id"));
					product.setPrice(result.getFloat("product_price"));
					product.setProductName(result.getString("product_name"));
					product.setUnit(result.getString("product_unit"));
					productList.put(product.getId(), product);
				}
			} catch (SQLException e) {
				productList = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting productList", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
		return productList;
	}

	public HashMap<Integer, Product> getProductStockList() {
		productStockList.clear();
		query = "SELECT * FROM product_stock WHERE  stock_status='ONLINE' order by product_id";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result == null) {
			productStockList = null;
		} else {
			try {
				while (result.next()) {
					Product product = new Product();
					product.setId(result.getInt("product_id"));
					product.setPrice(result.getFloat("product_price"));
					product.setProductName(result.getString("product_name"));
					product.setUnit(result.getString("product_unit"));
					product.setQuantity(result.getInt("product_quantity"));
					productStockList.put(product.getId(), product);
				}
			} catch (SQLException e) {
				productStockList = null;
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while getting productList", query);
				e.printStackTrace();
			} finally {
				JdbcConnection.getInstance().closeSQLConnection();
			}
		}
		return productStockList;
	}

	public boolean addStock(int quantity, Product product) {
		int rowsAffected = 0;
		if (productStockList.containsKey(product.getId())) {
			query = "update product_stock set product_quantity=product_quantity+" + quantity + " where product_id = "
					+ product.getId() + " and stock_status='ONLINE'";
			rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
			if (rowsAffected < 1) {
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update into table", query);
			}
		} else {
			query = "insert into product_stock (product_id, product_name, product_unit, product_price, product_quantity, stock_status) values("
					+ product.getId() + ",'" + product.getProductName().replace("'", "''") + "','"
					+ product.getUnit().replace("'", "''") + "','" + product.getPrice() + "'," + quantity
					+ ",'ONLINE')";
			rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
			if (rowsAffected < 1) {
				JdbcConnection.getInstance().printErrorMessageWithQuery("Error while insert into table", query);
			}
		}
		return (rowsAffected >= 1);
	}

	public boolean subtractStock(int quantity, Product product) {
		int rowsAffected = 0;
		query = "update product_stock set product_quantity=product_quantity-" + quantity + " where product_id = "
				+ product.getId() + " and stock_status='ONLINE'";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update quantity in table", query);
		}
		return (rowsAffected >= 1);
	}

	public boolean updateProductName(int productId, String updatedProductName) {
		int rowsAffected = 0;
		query = "update product_info set product_name='" + updatedProductName.replace("'", "''")
				+ "' where product_id = " + productId;
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update product name in table", query);
		}
		query = "update product_stock set product_name='" + updatedProductName.replace("'", "''")
				+ "' where product_id = " + productId;
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

		return (rowsAffected >= 1);
	}

	public boolean updateProductPrice(int productId, float updatedPrice) {
		int rowsAffected = 0;
		query = "update product_info set product_price=" + updatedPrice + " where product_id = " + productId;
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update product price in table", query);
		}
		query = "update product_stock set product_price=" + updatedPrice + " where product_id = " + productId;
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1);
	}

	public boolean updateProductUnit(int productId, String updatedUnit) {
		int rowsAffected = 0;
		query = "update product_info set product_unit='" + updatedUnit.replace("'", "''") + "' where product_id = "
				+ productId;
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update product units in table", query);
		}
		query = "update product_stock set product_unit='" + updatedUnit.replace("'", "''") + "' where product_id = "
				+ productId;
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1);
	}

	public boolean deleteProduct(int productId) {
		int rowsAffected = 0;
		query = "update product_info set product_status='OFFLINE' where product_id = " + productId;
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		if (rowsAffected < 1) {
			JdbcConnection.getInstance().printErrorMessageWithQuery("Error while update product status in table", query);
		}
		query = "update product_stock set stock_status='OFFLINE' where product_id = " + productId;
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return (rowsAffected >= 1);
	}

}
