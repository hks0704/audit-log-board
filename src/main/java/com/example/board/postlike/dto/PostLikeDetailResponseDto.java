package com.example.board.postlike.dto;

import java.time.LocalDateTime;

import com.example.board.postlike.model.PostLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDetailResponseDto {
	private Long id;
	private LocalDateTime createdDate;

	public PostLikeDetailResponseDto(PostLike entity) {
		this.id = entity.getId();
		this.createdDate = entity.getCreatedDate();
	}
}
