package com.example.board.login.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.exception.CustomValidateException;
import com.example.board.common.extractor.ClientInfoExtractor;
import com.example.board.geo.dto.GeoInfo;
import com.example.board.geo.service.GeoLocationService;
import com.example.board.geo.util.GeoUtils;
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

			// 현재 로그인 상황이 공인IP가 아닌지도 확인할 필요가 있음
			// SUCCESS, PRIVATE_IP, UNKNOWN
			// 최근 5번 로그인 내역을 바탕으로 로그인 위치 파악
			// 해외 로그인 | 국내 타지역 로그인 | 같은 위치 로그인
			List<LoginHistory> loginHistoryList = loginHistoryRepository.findTop5ByUserIdOrderByLoginTimeDesc(userId);

			Set<String> countryCodeSet = loginHistoryList.stream().map(LoginHistory::getCountryCode).collect(Collectors.toSet());

			if (loginHistoryList.size() == 0) {
				// 첫 로그인 사용자
			}

			log.info("[TEST] 최근 5개 국가 코드 집합: {}", countryCodeSet);

			if (!countryCodeSet.contains(geoInfo.getCountryCode())) {
				log.info("[TEST] 해외 로그인으로 간주");
			} else {
				// 현재 위치와 최근 위치 거리 계산
				double prevLat = loginHistoryList.get(0).getLatitude();
				double prevLng = loginHistoryList.get(0).getLongitude();
				double currLat = geoInfo.getLatitude();
				double currLng = geoInfo.getLongitude();

				double distance = GeoUtils.calculateDistance(prevLat, prevLng, currLat, currLng);

				if (distance >= 100) {
					// 새로운 지역 로그인
				} else {
					// 정상 로그인
				}

			}

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
