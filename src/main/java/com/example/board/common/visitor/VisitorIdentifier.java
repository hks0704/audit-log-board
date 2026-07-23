package com.example.board.common.visitor;

import org.springframework.stereotype.Component;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.util.HashUtil;

/**
 * 방문자의 좋아요 클릭시 중복 확인을 위한 SHA256 기반 식별자 생성에 사용
 */
@Component
public class VisitorIdentifier {

	public String generate(ClientInfo clientInfo) {
		String data = clientInfo.clientIp() + "|" + clientInfo.userAgent();
		return HashUtil.sha256(data);
	}
}
