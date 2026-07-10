package com.example.board.login.service;

import org.springframework.stereotype.Service;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.exception.CustomValidateException;
import com.example.board.common.extractor.ClientInfoExtractor;
import com.example.board.geo.dto.GeoInfo;
import com.example.board.geo.service.GeoLocationService;
import com.example.board.login.dto.LoginRequestDto;
import com.example.board.login.model.LoginHistory;
import com.example.board.login.repository.LoginHistoryRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

	private final ClientInfoExtractor clientInfoExtractor;
	private final LoginHistoryRepository loginHistoryRepository;
	private final GeoLocationService geoLocationService;

	public Long login(LoginRequestDto requestDto, HttpServletRequest httpRequest) {

		// DB 없이도 간단한 테스트 용
		if ("test".equals(requestDto.username()) && "1234".equals(requestDto.password())) {
			Long userId = 1L;

			ClientInfo clientInfo = clientInfoExtractor.extract(httpRequest);

			GeoInfo geoInfo = geoLocationService.lookup(clientInfo.clientIp());

			LoginHistory loginHistory = LoginHistory.of(
				userId,
				clientInfo.clientIp(),
				clientInfo.userAgent(),
				geoInfo
			);

			// log.info("호출된 지역코드 정보: {}", geoInfo);

			loginHistoryRepository.save(loginHistory);

			return userId;
		}

		throw new CustomValidateException("invalid login");
	}
}
