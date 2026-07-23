package com.example.board.postlike.service;

import com.example.board.postlike.dto.PostLikeDetailResponseDto;

import jakarta.servlet.http.HttpServletRequest;

public interface PostLikeService {
	PostLikeDetailResponseDto create(HttpServletRequest servletRequest, Long postId);
}
