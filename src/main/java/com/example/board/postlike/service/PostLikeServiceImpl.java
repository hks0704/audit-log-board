package com.example.board.postlike.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.exception.CustomNotFoundException;
import com.example.board.common.extractor.ClientInfoExtractor;
import com.example.board.post.model.Post;
import com.example.board.post.repository.PostRepository;
import com.example.board.postlike.dto.PostLikeDetailResponseDto;
import com.example.board.postlike.model.PostLike;
import com.example.board.postlike.repository.PostLikeRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {
	// CLIENT INFO 정보를 추출하는 의존성을 가져와서 HASH하기

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;
	private final ClientInfoExtractor clientInfoExtractor;

	@Override
	public PostLikeDetailResponseDto create(HttpServletRequest servletRequest, Long postId) {
		ClientInfo clientInfo = clientInfoExtractor.extract(servletRequest);

		// 이제 앞부분에 REDIS CACHE 검사 부분을 추가

		Post entity = postRepository.findById(postId).orElseThrow(
			() -> new CustomNotFoundException("Post not found with id: " + postId)
		);

		String userAgentHash = encryptSHA256(clientInfo);

		PostLike postLike = PostLike.create(entity, userAgentHash);

		return new PostLikeDetailResponseDto(postLikeRepository.save(postLike));
	}

	public String encryptSHA256(ClientInfo clientInfo) {
		String data = clientInfo.clientIp() + clientInfo.userAgent();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new CustomNotFoundException("SHA-256 알고리즘을 찾을 수 없습니다", e);
		}

	}
}
