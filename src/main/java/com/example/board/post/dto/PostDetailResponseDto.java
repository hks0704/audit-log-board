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
public class PostDetailResponseDto {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Long postLikeCount;

	public PostDetailResponseDto(Post entity, Long postLikeCount) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createdDate = entity.getCreatedDate();
		this.updatedDate = entity.getUpdatedDate();
		this.postLikeCount = postLikeCount;
	}
}
