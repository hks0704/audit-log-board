package com.example.board.accesslog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.accesslog.dto.AccessLogResponseDto;
import com.example.board.accesslog.model.AccessLog;
import com.example.board.accesslog.repository.AccessLogRepository;
import com.example.board.common.dto.ClientInfo;
import com.example.board.post.dto.PostDetailResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessLogServiceImpl implements AccessLogService {
	private final AccessLogRepository accessLogRepository;

	@Override
	public AccessLogResponseDto create(ClientInfo clientInfo) {

		AccessLog accessLog = new AccessLog(clientInfo);
		System.out.println("ERROR " + accessLog.getAccessTime());
		return new AccessLogResponseDto(accessLogRepository.save(accessLog));
	}

	@Override
	public List<AccessLogResponseDto> findAllDetails() {
		return accessLogRepository.findAll().stream().map(AccessLogResponseDto::new).toList();
	}

}
