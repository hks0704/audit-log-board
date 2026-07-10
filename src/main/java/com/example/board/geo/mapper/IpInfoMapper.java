package com.example.board.geo.mapper;

import org.springframework.stereotype.Component;

import com.example.board.geo.dto.GeoApiResponse;
import com.example.board.geo.dto.GeoInfo;
import com.example.board.geo.util.GeoUtils;

@Component
public class IpInfoMapper {

	public GeoInfo toGeoInfo(GeoApiResponse response) {
		Double lat = GeoUtils.getLat(response.getLoc().split(",")[0]);
		Double lng = GeoUtils.getLng(response.getLoc().split(",")[1]);
		String countryName = GeoUtils.getCountryName(response.getCountry());
		return GeoInfo.builder()
			.country(countryName)
			.countryCode(response.getCountry())
			.city(response.getCity())
			.latitude(lat)
			.longitude(lng)
			.build();
	}

}
