package com.example.board.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.post.dto.PostCreateRequestDto;
import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostListResponseDto;
import com.example.board.post.dto.PostUpdateRequestDto;
import com.example.board.post.service.PostReadService;
import com.example.board.post.service.PostWriteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostControllerV1 {
	private final PostReadService postReadService;
	private final PostWriteService postWriteService;

	@GetMapping("/api/v1/post/list")
	public List<PostListResponseDto> getOverviewPosts() {
		return postReadService.findAllLists();
	}

	@GetMapping("/api/v1/post/list/detail")
	public List<PostDetailResponseDto> getDetailedPosts() {
		return postReadService.findAllDetails();
	}

	@GetMapping("/api/v1/post/{id}")
	public PostDetailResponseDto getPost(@PathVariable Long id) {
		return postReadService.findById(id);
	}

	@PostMapping("/api/v1/post")
	public PostDetailResponseDto createPost(@RequestBody PostCreateRequestDto requestDto) {
		return postWriteService.create(requestDto);
	}

	@PutMapping("/api/v1/post")
	public PostDetailResponseDto updatePost(@RequestBody PostUpdateRequestDto requestDto) {
		return postWriteService.update(requestDto);
	}

	@DeleteMapping("/api/v1/post/{id}")
	public void deletePost(@PathVariable Long id) {
		postWriteService.delete(id);
	}
}
