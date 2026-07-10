package com.example.board.geo.util;

import java.util.Locale;
import java.util.Scanner;

public final class GeoUtils {
	private GeoUtils() {} // 인스턴스화 방지

	public static Double getLat(String latitude) {
		Scanner scanner = new Scanner(latitude);
		Double lat = scanner.hasNextDouble() ? scanner.nextDouble() : null;
		scanner.close();
		return lat;
	}

	public static Double getLng(String longitude) {
		Scanner scanner = new Scanner(longitude);
		Double lng = scanner.hasNextDouble() ? scanner.nextDouble() : null;
		scanner.close();
		return lng;
	}

	public static String getCountryName(String alpha2) {
		Locale locale = new Locale("", alpha2);
		return locale.getDisplayCountry(Locale.ENGLISH);
	}

}
