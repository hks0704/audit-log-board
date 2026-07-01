package com.example.board.post.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostListResponseDto;

public interface PostReadService {
	PostDetailResponseDto findById(Long id);
	List<PostListResponseDto> findAllLists();
	List<PostDetailResponseDto> findAllDetails();

	List<PostListResponseDto> findByTitle(String title);
	List<PostListResponseDto> findByContent(String content);
	// List<PostListResponseDto> findByTitleContaining(String title);
	// List<PostListResponseDto> findByContentContaining(String content);
	// List<PostListResponseDto> findByTitleContainingIgnoreCase(String title);
	// List<PostListResponseDto> findByContentContainingIgnoreCase(String content);
	//
	// List<PostListResponseDto> findByTitleAndContent(String title, String content);
	// List<PostListResponseDto> findByTitleContainingOrContentContaining(String title, String content);
	// List<PostListResponseDto> findByTitleContainingAndContentContaining(String title, String content);
	// List<PostListResponseDto> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
	// List<PostListResponseDto> findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase(String title, String content);
	//
	// List<PostListResponseDto> findByCreatedDateAfter(LocalDateTime date);
	// List<PostListResponseDto> findByCreatedDateBefore(LocalDateTime date);
	// List<PostListResponseDto> findByCreatedDateBetween(LocalDateTime sDate, LocalDateTime eDate);
	//
	// List<PostListResponseDto> findByUpdatedDateAfter(LocalDateTime date);
	// List<PostListResponseDto> findByUpdatedDateBefore(LocalDateTime date);
	// List<PostListResponseDto> findByUpdatedDateBetween(LocalDateTime sDate, LocalDateTime eDate);
}
