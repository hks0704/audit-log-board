package com.example.board.geo.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.board.common.exception.CustomValidateException;
import com.example.board.geo.dto.GeoApiResponse;
import com.example.board.geo.dto.GeoInfo;
import com.example.board.geo.mapper.IpInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IpApiGeoLocationService implements GeoLocationService {

	private final RestClient restClient;
	private final IpInfoMapper ipInfoMapper;

	@Override
	public GeoInfo lookup(String clientIp) {

		InetAddress address = null;
		try {
			address = InetAddress.getByName(clientIp);
		} catch (UnknownHostException e) {
			log.warn("IP Parsing Failed. ip={}", clientIp, e);
			return GeoInfo.unknown();
		}

		if (address.isLoopbackAddress()
			|| address.isSiteLocalAddress()
			|| address.isAnyLocalAddress()
			|| address.isLinkLocalAddress()) {
			return GeoInfo.privateIp();
		}

		GeoApiResponse response = restClient.get()
			.uri(String.format("https://ipinfo.io/%s/json/", clientIp))
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(GeoApiResponse.class);

		return ipInfoMapper.toGeoInfo(response);
	}
}
