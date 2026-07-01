package com.example.board.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.common.exception.CustomNotFoundException;
import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostListResponseDto;
import com.example.board.post.model.Post;
import com.example.board.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostReadServiceImpl implements PostReadService{

	private final PostRepository postRepository;

	@Override
	public PostDetailResponseDto findById(Long id) {
		Post entity = postRepository.findById(id).orElseThrow(
			() -> new CustomNotFoundException("Post not found with id: " + id));
		return new PostDetailResponseDto(entity);
	}

	@Override
	public List<PostListResponseDto> findAllLists() {
		return postRepository.findAll().stream().map(PostListResponseDto::new).toList();
	}

	@Override
	public List<PostDetailResponseDto> findAllDetails() {
		return postRepository.findAll().stream().map(PostDetailResponseDto::new).toList();
	}

	@Override
	public List<PostListResponseDto> findByTitle(String title) {
		return postRepository.findByTitle(title).stream().map(PostListResponseDto::new).toList();
	}

	@Override
	public List<PostListResponseDto> findByContent(String content) {
		return postRepository.findByTitle(content).stream().map(PostListResponseDto::new).toList();
	}
}
