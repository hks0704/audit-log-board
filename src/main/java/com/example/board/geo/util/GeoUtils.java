package com.example.board.geo.util;

import java.util.Locale;
import java.util.Scanner;

public final class GeoUtils {

	private static final double EARTH_RADIUS_KM = 6371.0;
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

	/**
	 * 하버사인 공식으로 두 좌표 사이 거리 계산
	 * 대규모 데이터에서 최적화는 SQL 로직으로 처리하는 것을 권장
	 * @param prevLat 첫 번째 좌표의 위도
	 * @param prevLng 첫 번째 좌표의 경도
	 * @param currLat 두 번째 좌표의 위도
	 * @param currLng 두 번째 좌표의 경도
	 * @return 두 좌표 사이 킬로미터 단위 거리
	 */
	public static double calculateDistance(double prevLat, double prevLng, double currLat, double currLng) {
		double prevLatRad = Math.toRadians(prevLat);
		double prevLngRad = Math.toRadians(prevLng);
		double currLatRad = Math.toRadians(currLat);
		double currLngRad = Math.toRadians(currLng);

		double latRadDiff = currLatRad - prevLatRad;
		double lngRadDiff = currLngRad - prevLngRad;

		double a = Math.sin(latRadDiff/2) * Math.sin(latRadDiff/2) +
			Math.cos(prevLatRad) * Math.cos(currLatRad) * Math.sin(lngRadDiff/2) * Math.sin(lngRadDiff/2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS_KM * c;
	}

}
