package com.example.board.post.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.board.post.dto.PostUpdateRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;

	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public Post update(PostUpdateRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		return this;
	}
}
