package com.example.board.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.common.exception.CustomNotFoundException;
import com.example.board.common.exception.CustomValidateException;
import com.example.board.post.dto.PostCreateRequestDto;
import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostUpdateRequestDto;
import com.example.board.post.model.Post;
import com.example.board.post.repository.PostRepository;
import com.example.board.postlike.repository.PostLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostWriteServiceImpl implements PostWriteService{

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;

	@Override
	public PostDetailResponseDto create(PostCreateRequestDto requestDto) {
		if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty())
			throw new CustomValidateException("Post title must not be empty.");
		if (requestDto.getContent() == null || requestDto.getContent().isEmpty())
			throw new CustomValidateException("Post content must not be empty.");

		return new PostDetailResponseDto(postRepository.save(requestDto.toEntity()), 0L);
	}

	@Override
	public PostDetailResponseDto update(PostUpdateRequestDto requestDto) {
		Post entity = postRepository.findById(requestDto.getId()).orElseThrow(
			() -> new CustomNotFoundException("Post not found with id: " + requestDto.getId()));
		Long postLikeCount = postLikeRepository.countByPost(entity);
		return new PostDetailResponseDto(postRepository.save(entity.update(requestDto)), postLikeCount);
	}

	@Override
	public void delete(Long id) {
		// if (!postRepository.existsById(id))
		// 	throw new CustomNotFoundException("Cannot delete Post. Post not found with id: " + id);

		Post post = postRepository.findById(id).orElseThrow(
			() -> new CustomNotFoundException("Cannot delete Post. Post not found with id: " + id)
		);

		postLikeRepository.deleteByPost(post);
		postRepository.deleteById(id);
	}
}
