package com.example.board.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.common.exception.CustomNotFoundException;
import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostListResponseDto;
import com.example.board.post.model.Post;
import com.example.board.post.repository.PostRepository;
import com.example.board.postlike.repository.PostLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostReadServiceImpl implements PostReadService{

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;

	@Override
	public PostDetailResponseDto findById(Long id) {
		Post entity = postRepository.findById(id).orElseThrow(
			() -> new CustomNotFoundException("Post not found with id: " + id));
		Long postLikeCount = postLikeRepository.countByPost(entity);
		return new PostDetailResponseDto(entity, postLikeCount);
	}

	@Override
	public List<PostListResponseDto> findAllLists() {
		// LIST를 가져오고 null 체크해서 return
		return postRepository.findAllPostListResponseDtos();
		// return postRepository.findAll().stream().map(PostListResponseDto::new).toList();
	}

	@Override
	public List<PostDetailResponseDto> findAllDetails() {
		return postRepository.findAllPostDetailListResponseDtos();
		// return postRepository.findAll().stream().map(PostDetailResponseDto::new).toList();
	}

	@Override
	public List<PostListResponseDto> findByTitle(String title) {
		return postRepository.findPostListResponseDtosByTitle(title);
		// return postRepository.findByTitle(title).stream().map(PostListResponseDto::new).toList();
	}

	@Override
	public List<PostListResponseDto> findByContent(String content) {
		return postRepository.findPostListResponseDtosByContent(content);
		// return postRepository.findByTitle(content).stream().map(PostListResponseDto::new).toList();
	}
}
