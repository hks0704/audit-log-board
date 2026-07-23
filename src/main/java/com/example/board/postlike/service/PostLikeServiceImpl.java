package com.example.board.postlike.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.example.board.common.dto.ClientInfo;
import com.example.board.common.exception.CustomNotFoundException;
import com.example.board.common.extractor.ClientInfoExtractor;
import com.example.board.common.redis.RateLimitService;
import com.example.board.common.util.HashUtil;
import com.example.board.common.visitor.VisitorIdentifier;
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
	private final VisitorIdentifier visitorIdentifier;
	private final RateLimitService rateLimitService;

	@Override
	public PostLikeDetailResponseDto create(HttpServletRequest servletRequest, Long postId) {
		ClientInfo clientInfo = clientInfoExtractor.extract(servletRequest);

		// 이제 앞부분에 REDIS CACHE 검사 부분을 추가

		Post entity = postRepository.findById(postId).orElseThrow(
			() -> new CustomNotFoundException("Post not found with id: " + postId)
		);

		String userAgentHash = visitorIdentifier.generate(clientInfo);

		rateLimitService.checkLimit(userAgentHash);

		PostLike postLike = PostLike.create(entity, userAgentHash);

		return new PostLikeDetailResponseDto(postLikeRepository.save(postLike));
	}

}
