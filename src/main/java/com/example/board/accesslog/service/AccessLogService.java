package com.example.board.accesslog.service;

import java.util.List;

import com.example.board.accesslog.dto.AccessLogResponseDto;
import com.example.board.common.dto.ClientInfo;

public interface AccessLogService {
	AccessLogResponseDto create(ClientInfo clientInfo);
	List<AccessLogResponseDto> findAllDetails();
}
