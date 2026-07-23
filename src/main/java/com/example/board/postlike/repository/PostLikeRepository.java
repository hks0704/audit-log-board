package com.example.board.postlike.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.post.model.Post;
import com.example.board.postlike.model.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

	Long deleteByPost(Post post);

	Long countByPost(Post post);

	Optional<PostLike> findByUserAgentHash(String userAgentHash);

	Boolean existsByUserAgentHash(String userAgentHash);
}
