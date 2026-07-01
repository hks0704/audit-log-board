package com.example.board.post.service;

import com.example.board.post.dto.PostCreateRequestDto;
import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostUpdateRequestDto;

public interface PostWriteService {
	PostDetailResponseDto create(PostCreateRequestDto requestDto);
	PostDetailResponseDto update(PostUpdateRequestDto requestDto);
	void delete(Long id);
}
