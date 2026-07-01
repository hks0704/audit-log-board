package com.example.board.post.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.board.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


	// 기본 메서드
	List<Post> findByTitle(String title);
	List<Post> findByContent(String content);
	List<Post> findByTitleContaining(String title);
	List<Post> findByContentContaining(String content);
	List<Post> findByTitleContainingIgnoreCase(String title);
	List<Post> findByContentContainingIgnoreCase(String content);

	// 복합 메서드
	List<Post> findByTitleAndContent(String title, String content);
	List<Post> findByTitleContainingOrContentContaining(String title, String content);
	List<Post> findByTitleContainingAndContentContaining(String title, String content);
	List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
	List<Post> findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase(String title, String content);

	// 날짜 기반 메서드 (생성 일자 기준)
	List<Post> findByCreatedDateAfter(LocalDateTime date);
	List<Post> findByCreatedDateBefore(LocalDateTime date);
	List<Post> findByCreatedDateBetween(LocalDateTime sDate, LocalDateTime eDate);
	// 날짜 기반 메서드 (수정 일자 기준)
	List<Post> findByUpdatedDateAfter(LocalDateTime date);
	List<Post> findByUpdatedDateBefore(LocalDateTime date);
	List<Post> findByUpdatedDateBetween(LocalDateTime sDate, LocalDateTime eDate);
}
