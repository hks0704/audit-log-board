package com.example.board.login.service;

import org.springframework.stereotype.Service;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.exception.CustomValidateException;
import com.example.board.common.extractor.ClientInfoExtractor;
import com.example.board.login.dto.LoginRequestDto;
import com.example.board.login.model.LoginHistory;
import com.example.board.login.repository.LoginHistoryRepository;
import com.example.board.post.dto.PostCreateRequestDto;
import com.example.board.post.dto.PostDetailResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final ClientInfoExtractor clientInfoExtractor;
	private final LoginHistoryRepository loginHistoryRepository;

	public Long login(LoginRequestDto requestDto, HttpServletRequest httpRequest) {

		// DB 없이도 간단한 테스트 용
		if ("test".equals(requestDto.username()) && "1234".equals(requestDto.password())) {
			Long userId = 1L;

			ClientInfo clientInfo = clientInfoExtractor.extract(httpRequest);

			LoginHistory loginHistory = LoginHistory.builder()
				.userId(userId)
				.clientIp(clientInfo.clientIp())
				.userAgent(clientInfo.userAgent())
				.build();

			loginHistoryRepository.save(loginHistory);

			return userId;
		}

		throw new CustomValidateException("invalid login");
	}
}
