package com.example.board.geo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GeoApiResponse {

	private String clientIp;

	private String city;

	private String region;

	private String country;

	private String loc;

	private String postal;

	private String timezone;

	private String readme;
}
