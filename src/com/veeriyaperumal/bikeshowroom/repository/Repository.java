package com.veeriyaperumal.bikeshowroom.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.veeriyaperumal.bikeshowroom.BikeViewModel;

public class Repository {
	private static Repository repository;
	private BikeViewModel bikeViewModel;
	private static JSONObject jsonObject;
	private List<String> companyNameList, bikeSegmentList, bikesList, bikeDataList;
	private String bikeData;

	private Repository() {
		companyNameList = new ArrayList();
		bikeSegmentList = new ArrayList();
		bikesList = new ArrayList();
		bikeDataList = new ArrayList();
		bikeData = "";
		fetchData();
	}

	public static Repository getInstance(BikeViewModel bikeViewModel) {
		if (repository == null) {
			repository = new Repository();
			repository.bikeViewModel = bikeViewModel;
		}
		return repository;
	}

	public List<String> getMenus() {
		if (bikeViewModel.getTopOfChatHistory() == null) {
			return null;
		}
		switch (bikeViewModel.getTopOfChatHistory().getLevel()) {
		case 0:
			return companyNameList;
		case 1:

			return bikeSegmentList;
		case 2:
			fetchBikeName();
			return bikesList;
		case 3:
			fetchBikeData();
			bikeViewModel.showBikeData(bikeData);
			return bikeDataList;
		}
		return null;
	}

	private void fetchData() {
		getJsonObject();
		fetchBikeCompanyName();
		fetchBikeSegmentName();
	}

	private void fetchBikeCompanyName() {
		companyNameList.clear();
		JSONObject obj = (JSONObject) jsonObject.get("1");
		copyJsonArrayToList((JSONArray) obj.get("Company Name"), companyNameList);
		companyNameList.add("Exit");
	}

	private void fetchBikeSegmentName() {
		bikeSegmentList.clear();
		JSONObject obj = (JSONObject) jsonObject.get("2");
		copyJsonArrayToList((JSONArray) obj.get("Bike Segment"), bikeSegmentList);
		bikeSegmentList.add("Go back");
		bikeSegmentList.add("Exit");
	}

	private void fetchBikeName() {
		bikesList.clear();
		String temp = bikeViewModel.getTopOfChatHistory().getOption();
		JSONObject obj = (JSONObject) jsonObject.get("3");
		copyJsonArrayToList((JSONArray) obj.get(temp), bikesList);
		bikesList.add("Go back");
		bikesList.add("Exit");
	}

	private void fetchBikeData() {
		bikeDataList.clear();
		String temp = bikeViewModel.getTopOfChatHistory().getBikeName();
		JSONObject obj = (JSONObject) jsonObject.get("4");
		bikeData = (String) obj.get(temp);
		bikeDataList.add("Go back");
		bikeDataList.add("Exit");
	}

	private void copyJsonArrayToList(JSONArray jsonArray, List<String> list) {
		for (Object object : jsonArray) {
			list.add((String) object);
		}
	}

	private void getJsonObject() { // Create json object and take json data from local file.
		String filePath;
		JSONParser jsonParser = new JSONParser();
		try {
			filePath = new File(".").getCanonicalPath()
					+ "\\src\\com\\veeriyaperumal\\bikeshowroom\\repository\\jsondata\\bikedata.json";
		} catch (IOException e) {
			e.printStackTrace();
			bikeViewModel.terminateProgramWithMessage(
					"JSON data missing or Loan data folder not able to find.So program terminated automatically.");
			return;
		}
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			bikeViewModel.terminateProgramWithMessage(
					"While Parsing JSON object error occured.So program terminated automatically.");
			return;
		}
	}

}
