package com.example.board.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.dto.CommonResponse;
import com.example.board.post.dto.PostCreateRequestDto;
import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostListResponseDto;
import com.example.board.post.dto.PostUpdateRequestDto;
import com.example.board.post.service.PostReadService;
import com.example.board.post.service.PostWriteService;
import com.example.board.postlike.dto.PostLikeDetailResponseDto;
import com.example.board.postlike.service.PostLikeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/post")
public class PostControllerV2 {

	private final PostReadService postReadService;
	private final PostWriteService postWriteService;
	private final PostLikeService postLikeService;

	@GetMapping("/list")
	public ResponseEntity<CommonResponse<?>> getOverviewPosts() {
		List<PostListResponseDto> responseDto = postReadService.findAllLists();
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}

	@GetMapping("/list/detail")
	public ResponseEntity<CommonResponse<?>> getDetailedPosts() {
		List<PostDetailResponseDto> responseDto = postReadService.findAllDetails();
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponse<?>> getPost(@PathVariable Long id) {
		PostDetailResponseDto responseDto = postReadService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}

	@PostMapping
	public ResponseEntity<CommonResponse<?>> createPost(@RequestBody PostCreateRequestDto requestDto) {
		PostDetailResponseDto responseDto = postWriteService.create(requestDto);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}

	@PutMapping
	public ResponseEntity<CommonResponse<?>> updatePost(@RequestBody PostUpdateRequestDto requestDto) {
		PostDetailResponseDto responseDto = postWriteService.update(requestDto);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponse<?>> deletePost(@PathVariable Long id) {
		postWriteService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success());
	}

	// POST /articles/15/like
	@PostMapping("/like/{id}")
	public ResponseEntity<CommonResponse<?>> createPostLike(@PathVariable Long id, HttpServletRequest request) {

		PostLikeDetailResponseDto responseDto = postLikeService.create(request, id);
		return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
	}
}
