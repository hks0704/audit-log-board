package com.example.board.geo.dto;

import com.example.board.geo.type.GeoInfoStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GeoInfo {

	private String country;

	private String countryCode;

	private String city;

	private Double latitude;

	private Double longitude;

	@Builder.Default
	private GeoInfoStatus status = GeoInfoStatus.SUCCESS;

	public static GeoInfo privateIp() {
		return GeoInfo.builder()
			.status(GeoInfoStatus.PRIVATE_IP)
			.build();
	}

	public static GeoInfo unknown() {
		return GeoInfo.builder()
			.status(GeoInfoStatus.UNKNOWN)
			.build();
	}

	public boolean isPrivate() {
		return status == GeoInfoStatus.PRIVATE_IP;
	}

	public boolean isUnknown() {
		return status == GeoInfoStatus.UNKNOWN;
	}

	public boolean isSuccess() {
		return status == GeoInfoStatus.SUCCESS;
	}

}
