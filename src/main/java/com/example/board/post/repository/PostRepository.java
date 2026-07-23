package com.example.board.post.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.board.post.dto.PostDetailResponseDto;
import com.example.board.post.dto.PostListResponseDto;
import com.example.board.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {




	// 좋아요 결과를 포함한 내용을 Dto로 출력
	@Query(value = """
		select new com.example.board.post.dto.PostListResponseDto(
		p.id, p.title, p.createdDate, p.updatedDate, count(pl.id)
		)
		from Post p
		left join PostLike pl
		on p.id = pl.post.id
		group by 
		p.id, p.title, p.createdDate, p.updatedDate
		order by p.createdDate desc
		""")
	List<PostListResponseDto> findAllPostListResponseDtos();

	@Query(value = """
		select new com.example.board.post.dto.PostDetailResponseDto(
		p.id, p.title, p.content, p.createdDate, p.updatedDate, count(pl.id)
		)
		from Post p
		left join PostLike pl
		on p.id = pl.post.id
		group by 
		p.id, p.title, p.content, p.createdDate, p.updatedDate
		order by p.createdDate desc
		""")
	List<PostDetailResponseDto> findAllPostDetailListResponseDtos();

	@Query(value = """
		select new com.example.board.post.dto.PostListResponseDto(
		p.id, p.title, p.createdDate, p.updatedDate, count(pl.id)
		)
		from Post p
		left join PostLike pl
		on p.id = pl.post.id
		where p.title = :title
		group by 
		p.id, p.title, p.createdDate, p.updatedDate
		order by p.createdDate desc
		""")
	List<PostListResponseDto> findPostListResponseDtosByTitle(@Param("title") String title);

	@Query(value = """
		select new com.example.board.post.dto.PostListResponseDto(
		p.id, p.title, p.createdDate, p.updatedDate, count(pl.id)
		)
		from Post p
		left join PostLike pl
		on p.id = pl.post.id
		where p.content = :content
		group by 
		p.id, p.title, p.createdDate, p.updatedDate
		order by p.createdDate desc
		""")
	List<PostListResponseDto> findPostListResponseDtosByContent(@Param("content") String content);

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
