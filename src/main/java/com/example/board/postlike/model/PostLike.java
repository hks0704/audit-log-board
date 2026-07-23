package com.example.board.postlike.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.board.post.model.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	// ip, Agent 바탕 SHA256 기반 식별자 생성
	@Column(nullable = false, length = 64)
	private String userAgentHash;

	@CreationTimestamp
	private LocalDateTime createdDate;

	public static PostLike create(Post post, String userAgentHash) {
		return PostLike.builder()
			.post(post)
			.userAgentHash(userAgentHash)
			.build();
	}

}
