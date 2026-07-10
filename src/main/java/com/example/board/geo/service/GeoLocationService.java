package com.example.board.geo.service;

import java.net.UnknownHostException;

import com.example.board.geo.dto.GeoInfo;

public interface GeoLocationService {
	GeoInfo lookup(String clientIp);
}
