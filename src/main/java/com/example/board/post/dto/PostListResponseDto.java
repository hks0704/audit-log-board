package com.example.board.post.dto;

import java.time.LocalDateTime;

import com.example.board.post.model.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponseDto {
	private Long id;
	private String title;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Long postLikeCount;

	public PostListResponseDto(Post entity, Long postLikeCount) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
		this.postLikeCount = postLikeCount;
	}
}
