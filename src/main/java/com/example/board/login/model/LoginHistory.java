package com.example.board.login.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.board.geo.dto.GeoInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class LoginHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false, length = 50)
	private String clientIp;

	@Column(nullable = false, length = 500)
	private String userAgent;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime loginTime;

	private String country;

	private String countryCode;

	private String city;

	private Double latitude;

	private Double longitude;

	public static LoginHistory of(Long userId, String clientIp, String userAgent, GeoInfo geoInfo) {
		return LoginHistory.builder()
			.userId(userId)
			.clientIp(clientIp)
			.userAgent(userAgent)
			.country(geoInfo.getCountry())
			.countryCode(geoInfo.getCountryCode())
			.city(geoInfo.getCity())
			.latitude(geoInfo.getLatitude())
			.longitude(geoInfo.getLongitude())
			.build();
	}

}
